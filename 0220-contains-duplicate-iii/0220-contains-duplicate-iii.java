import java.util.*;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, long valueDiff) {
        TreeSet<Long> window = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];

            // is number se just chhota ya barabar element dhoondo
            Long floor = window.floor(num);
            if (floor != null && num - floor <= valueDiff) {
                return true;
            }

            // is number se just bada element dhoondo
            Long ceiling = window.ceiling(num);
            if (ceiling != null && ceiling - num <= valueDiff) {
                return true;
            }

            window.add(num);

            // window size ko indexDiff tak maintain karo (purana element hatao)
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }
}