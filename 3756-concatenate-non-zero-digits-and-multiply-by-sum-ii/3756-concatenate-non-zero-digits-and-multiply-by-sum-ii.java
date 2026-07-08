class Solution {
    static final int MOD = 1000000007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long[] prefSum = new long[n + 1]; // digits ka sum
        long[] prefNum = new long[n + 1]; // non-zero digits ko jodne wala number
        int[] cnt = new int[n + 1]; // kitne non-zero digits hain
        long[] pow10 = new long[n + 1]; // 10 ki power mod ke liye

        pow10[0] = 1;

        for(int i = 0; i < n; i++){
            int digit = s.charAt(i) - '0';
            prefSum[i + 1] = (prefSum[i] + digit) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
            cnt[i + 1] = cnt[i];

            if(digit!= 0){
                cnt[i + 1]++;
                prefNum[i + 1] = (prefNum[i] * 10 + digit) % MOD;
            } else {
                prefNum[i + 1] = prefNum[i]; // 0 ko skip kar do
            }
        }

        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int l = queries[i][0];
            int r = queries[i][1];

            long sum = (prefSum[r + 1] - prefSum[l] + MOD) % MOD;
            int nonZeroCnt = cnt[r + 1] - cnt[l];

            if(nonZeroCnt == 0){
                ans[i] = 0;
                continue;
            }

            // [l,r] ka number nikalna hai
            // prefNum[r+1] - prefNum[l] * 10^nonZeroCnt
            long num = (prefNum[r + 1] - (prefNum[l] * pow10[nonZeroCnt]) % MOD + MOD) % MOD;

            ans[i] = (int)((num * sum) % MOD);
        }
        return ans;
    }
}