class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int papers = n - mid; // mid se end tak kitne papers

            if(citations[mid] == papers) {
                return papers; // perfect match
            } else if(citations[mid] < papers) {
                // citations kam hain, to h-index bada chahiye
                // right side jao
                left = mid + 1;
            } else {
                // citations zyada hain, to h-index kam ho sakta hai
                // left side jao
                right = mid - 1;
            }
        }
        return n - left; // left hi answer hai
    }
}