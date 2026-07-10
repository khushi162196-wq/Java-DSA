import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: value ke sath original index bhi rakho, aur sort karo
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> nums[a] - nums[b]);

        int[] sortedNums = new int[n];
        int[] indexMap = new int[n]; // indexMap[originalIndex] = position in sorted order
        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[order[i]];
            indexMap[order[i]] = i;
        }

        // Step 2: har sorted position se ek jump mein farthest kaha tak pahunch sakte hain
        int maxLevel = 1;
        while ((1 << maxLevel) < n) maxLevel++;
        maxLevel += 1;

        int[][] jump = new int[n][maxLevel];
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (right < i) right = i;
            while (right + 1 < n && sortedNums[right + 1] - sortedNums[i] <= maxDiff) right++;
            jump[i][0] = right;
        }

        // Step 3: binary lifting table banao
        for (int level = 1; level < maxLevel; level++) {
            for (int i = 0; i < n; i++) {
                jump[i][level] = jump[jump[i][level - 1]][level - 1];
            }
        }

        // Step 4: har query ka answer nikalo
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = indexMap[queries[q][0]];
            int v = indexMap[queries[q][1]];
            int a = Math.min(u, v), b = Math.max(u, v);

            if (a == b) {
                ans[q] = 0;
                continue;
            }

            int cur = a, steps = 0;
            for (int level = maxLevel - 1; level >= 0; level--) {
                if (jump[cur][level] < b) {
                    cur = jump[cur][level];
                    steps += (1 << level);
                }
            }

            if (cur < b) {
                if (jump[cur][0] >= b) {
                    steps += 1;
                } else {
                    steps = -1; // unreachable
                }
            }

            ans[q] = steps;
        }

        return ans;
    }
}