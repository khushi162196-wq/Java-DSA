import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // Step 1: dp array. dp[i] = min coins for amount i
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // impossible value se fill karo
        dp[0] = 0; // 0 amount ke liye 0 coins

        // Step 2: 1 se amount tak har amount ke liye calculate karo
        for (int i = 1; i <= amount; i++) {
            // har coin try karo
            for (int coin : coins) {
                if (i - coin >= 0) { // coin use kar sakte hain
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // Step 3: Agar answer change nahi hua to impossible hai
        return dp[amount] > amount? -1 : dp[amount];
    }
}