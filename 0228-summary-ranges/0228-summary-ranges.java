import java.util.*;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int n = nums.length;
        int i = 0;

        while(i < n) {
            int start = nums[i]; // range ka start

            // jab tak continuous hai i ko aage badhao
            while(i + 1 < n && nums[i + 1] == nums[i] + 1) {
                i++;
            }

            int end = nums[i]; // range ka end

            // 1 element hai to "a", warna "a->b"
            if(start == end) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "->" + end);
            }

            i++; // next range ke liye
        }
        return result;
    }
}