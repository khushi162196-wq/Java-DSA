import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Step 1: Sort karo
        // Rule: start asc, agar start same hai to end desc
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // bada end pehle
            }
            return a[0] - b[0]; // chota start pehle
        });
        
        int count = 0;
        int maxEnd = 0;
        
        // Step 2: Check karo covered hai ya naya interval hai
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            if (end > maxEnd) { // naya interval mila
                count++;
                maxEnd = end; // maxEnd update
            }
            // else: ye covered hai, skip
        }
        
        return count;
    }
    
    // Testing ke liye main
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] intervals = {{1,4}, {3,6}, {2,8}};
        System.out.println("Remaining intervals: " + sol.removeCoveredIntervals(intervals));
        // Output: 1
    }
}