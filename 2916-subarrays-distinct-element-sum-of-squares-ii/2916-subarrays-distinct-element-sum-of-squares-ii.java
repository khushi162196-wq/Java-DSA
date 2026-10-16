import java.util.Arrays;

class Solution {
    private static final long MOD = 1_000_000_007;

    private long[] sumSq;
    private long[] sum;
    private long[] lazy;

    private void apply(int node, int l, int r, long val) {
        val %= MOD;
        long len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2 * val % MOD * sum[node] % MOD + val * val % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + val * len % MOD) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sum = new long[4 * n];
        lazy = new long[4 * n];

        // Find max element to initialize index array efficiently
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) maxVal = x;
        }

        int[] lastPos = new int[maxVal + 1];
        Arrays.fill(lastPos, -1);

        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int prev = lastPos[x];
            int ql = prev + 1;
            int qr = j;

            update(1, 0, n - 1, ql, qr, 1);
            lastPos[x] = j;

            totalSum = (totalSum + sumSq[1]) % MOD;
        }

        return (int) totalSum;
    }
}