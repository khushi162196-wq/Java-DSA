import java.util.*;

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] ans = new int[k];

        // i = nums1 se kitne digit lenge, k-i = nums2 se
        int start = Math.max(0, k - n2);
        int end = Math.min(k, n1);

        for (int i = start; i <= end; i++) {
            int[] seq1 = maxSubsequence(nums1, i); // Step 1
            int[] seq2 = maxSubsequence(nums2, k - i);
            int[] candidate = merge(seq1, seq2); // Step 2

            if (greater(candidate, 0, ans, 0)) { // Step 3
                ans = candidate;
            }
        }
        return ans;
    }

    // Step 1: Array se k digits ki max subsequence nikalo - Monotonic Stack
    private int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int drop = n - k; // kitne elements drop kar sakte hain

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && drop > 0) {
                top--;
                drop--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                drop--;
            }
        }
        return stack;
    }

    // Step 2: 2 subsequence ko merge karo to get max
    private int[] merge(int[] nums1, int[] nums2) {
        int k = nums1.length + nums2.length;
        int[] res = new int[k];
        int i = 0, j = 0, r = 0;

        while (i < nums1.length || j < nums2.length) {
            res[r++] = greater(nums1, i, nums2, j)? nums1[i++] : nums2[j++];
        }
        return res;
    }

    // Compare karo ki nums1[i...] bada hai ya nums2[j...]
    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}