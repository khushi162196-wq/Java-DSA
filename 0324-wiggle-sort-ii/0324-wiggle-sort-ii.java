import java.util.*;

class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // Step 1: Sort

        int[] res = new int[n];
        int mid = (n - 1) / 2; // left half ka end
        int end = n - 1; // right half ka end

        // Step 2: Pehle chote elements even index pe, bade odd index pe
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                res[i] = nums[mid--]; // chota
            } else {
                res[i] = nums[end--]; // bada
            }
        }

        // Step 3: wapas copy kar do
        for (int i = 0; i < n; i++) {
            nums[i] = res[i];
        }
    }
}