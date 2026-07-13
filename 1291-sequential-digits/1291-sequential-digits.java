import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        
        // start with digits 1 to 9
        for (int i = 1; i <= 9; i++) q.offer(i);
        
        while (!q.isEmpty()) {
            int num = q.poll();
            
            if (num >= low && num <= high) ans.add(num);
            if (num > high) continue;
            
            int lastDigit = num % 10;
            // next digit must be lastDigit + 1
            if (lastDigit < 9) {
                int nextNum = num * 10 + (lastDigit + 1);
                q.offer(nextNum);
            }
        }
        return ans;
    }
}