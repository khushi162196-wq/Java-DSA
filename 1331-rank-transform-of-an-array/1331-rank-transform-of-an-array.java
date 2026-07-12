import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if(n == 0) return arr;

        // 1. Copy karke sort karo
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // 2. Har unique number ko rank do using HashMap
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for(int num : sorted) {
            if(!rankMap.containsKey(num)) { // duplicate skip
                rankMap.put(num, rank);
                rank++;
            }
        }

        // 3. Original array ko rank se replace karo
        int[] result = new int[n];
        for(int i = 0; i < n; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }
}