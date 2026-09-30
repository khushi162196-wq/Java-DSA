import java.util.TreeSet;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int result = Integer.MIN_VALUE;

        // Iterate over the smaller dimension to minimize outer loops: O(min(m, n)^2)
        boolean colMajor = m > n;
        int outerBound = colMajor ? n : m;
        int innerBound = colMajor ? m : n;

        for (int i = 0; i < outerBound; i++) {
            int[] sums = new int[innerBound];
            for (int j = i; j < outerBound; j++) {
                // Accumulate row/column sums between boundaries i and j
                for (int c = 0; c < innerBound; c++) {
                    sums[c] += colMajor ? matrix[c][j] : matrix[j][c];
                }

                // 1. Kadane's Optimization:
                // If the absolute maximum subarray sum is <= k, it is optimal for this span
                int kadaneMax = kadane(sums);
                if (kadaneMax <= k) {
                    result = Math.max(result, kadaneMax);
                    if (result == k) return k;
                    continue;
                }

                // 2. Fallback to TreeSet for maximum subarray sum <= k
                TreeSet<Integer> prefixSet = new TreeSet<>();
                prefixSet.add(0);
                int currentSum = 0;

                for (int val : sums) {
                    currentSum += val;
                    // We need prefix sum S such that: currentSum - S <= k  =>  S >= currentSum - k
                    Integer target = prefixSet.ceiling(currentSum - k);
                    if (target != null) {
                        result = Math.max(result, currentSum - target);
                        if (result == k) return k;
                    }
                    prefixSet.add(currentSum);
                }
            }
        }

        return result;
    }

    private int kadane(int[] nums) {
        int maxEndingHere = 0;
        int maxSoFar = Integer.MIN_VALUE;
        for (int num : nums) {
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}