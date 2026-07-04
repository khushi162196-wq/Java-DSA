import java.util.*;

class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {

        if (x == 0) return 0;

        TreeSet<Integer> set = new TreeSet<>();
        int ans = Integer.MAX_VALUE;

        for (int i = x; i < nums.size(); i++) {

            set.add(nums.get(i - x));

            Integer floor = set.floor(nums.get(i));
            if (floor != null) {
                ans = Math.min(ans, nums.get(i) - floor);
            }

            Integer ceil = set.ceiling(nums.get(i));
            if (ceil != null) {
                ans = Math.min(ans, ceil - nums.get(i));
            }
        }

        return ans;
    }
}