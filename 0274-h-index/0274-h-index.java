import java.util.*;

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations); // chote se bade
        int n = citations.length;

        for(int i = 0; i < n; i++) {
            int papers = n - i; // i se end tak kitne papers
            int cites = citations[i]; // is paper ke citations

            if(cites >= papers) {
                return papers;
            }
        }
        return 0;
    }
}