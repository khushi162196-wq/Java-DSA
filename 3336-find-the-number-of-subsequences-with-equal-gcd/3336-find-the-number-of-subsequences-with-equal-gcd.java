import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    static final int MAXV = 200; // nums[i] <= 200 as per constraints

    public int subsequencePairCount(int[] nums) {
        // dp[g1][g2]: number of ways where seq1's current gcd = g1, seq2's current gcd = g2
        // g1 = 0 means seq1 is still empty, same for g2
        long[][] dp = new long[MAXV + 1][MAXV + 1];
        dp[0][0] = 1; // both sequences empty initially

        for (int x : nums) {
            long[][] ndp = new long[MAXV + 1][MAXV + 1];
            for (int g1 = 0; g1 <= MAXV; g1++) {
                for (int g2 = 0; g2 <= MAXV; g2++) {
                    long val = dp[g1][g2];
                    if (val == 0) continue;
                    // 1. x ko kisi bhi sequence me na daalo
                    ndp[g1][g2] = (ndp[g1][g2] + val) % MOD;
                    // 2. x ko seq1 me daalo
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    ndp[ng1][g2] = (ndp[ng1][g2] + val) % MOD;

                    // 3. x ko seq2 me daalo
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    ndp[g1][ng2] = (ndp[g1][ng2] + val) % MOD;
                }
            }
            dp = ndp;
        }

        // Answer: dono sequences non-empty hain aur gcd barabar hai
        long ans = 0;
        for (int g = 1; g <= MAXV; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}