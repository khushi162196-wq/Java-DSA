class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        int total = n + m;

        int i = 0;
        int j = 0;

        int prev = 0;
        int curr = 0;

        for (int count = 0; count <= total / 2; count++) {

            prev = curr;

            if (i < n && (j >= m || nums1[i] <= nums2[j])) {
                curr = nums1[i];
                i++;
            } 
            else {
                curr = nums2[j];
                j++;
            }
        }

        if (total % 2 == 0) {
            return (prev + curr) / 2.0;
        }

        return curr;
    }
}