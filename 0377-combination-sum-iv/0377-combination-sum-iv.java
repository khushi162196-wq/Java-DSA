class Solution {
    public int combinationSum4(int[] nums, int target) {
        // dp[i] represents the number of combinations that add up to i
        int[] dp = new int[target + 1];
        // Base case: There is 1 way to make a sum of 0 (using an empty set)
        dp[0] = 1;
        // Iterate through all sub-targets from 1 to target
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}