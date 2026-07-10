import java.util.*;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (lastIndex.containsKey(num) && i - lastIndex.get(num) <= k) {
                return true;
            }

            lastIndex.put(num, i); // is number ka latest index update karo
        }

        return false;
    }
}