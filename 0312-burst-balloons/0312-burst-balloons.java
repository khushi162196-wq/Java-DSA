class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        // Add dummy 1 at both ends. Makes boundary handling easy
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 0; i < n; i++) arr[i + 1] = nums[i];
        
        int m = n + 2;
        int[][] dp = new int[m][m]; // dp[l][r] = max coins from l to r
        
        // length = size of interval
        for (int len = 2; len < m; len++) { // interval length
            for (int left = 0; left + len < m; left++) {
                int right = left + len;
                
                // try every balloon 'k' as the LAST one to burst in [left+1, right-1]
                for (int k = left + 1; k < right; k++) {
                    int coins = arr[left] * arr[k] * arr[right];
                    coins += dp[left][k] + dp[k][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }
        
        return dp[0][m - 1]; // burst all balloons between 0 and m-1
    }
}