
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] freq = new long[100001];

        long totalK = (long) k1 + k2;
        long sum = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            sum += diff;
            maxDiff = Math.max(maxDiff, diff);
        }

        if (totalK >= sum) {
            return 0L;
        }

        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            long moves = Math.min(freq[d], totalK);
            freq[d] -= moves;
            freq[d - 1] += moves;
            totalK -= moves;
        }

        long result = 0;

        for (int d = 1; d <= maxDiff; d++) {
            result += freq[d] * d * d;
        }

        return result;
    }
}

