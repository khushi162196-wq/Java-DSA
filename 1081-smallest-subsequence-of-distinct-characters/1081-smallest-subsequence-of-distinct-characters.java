import java.util.*;

class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();

        // 1. Har char ki last occurrence store karo
        int[] lastIndex = new int[26];
        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] inStack = new boolean[26]; // char stack me hai ya nahi
        Stack<Character> stack = new Stack<>();

        // 2. String traverse karo
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            // Agar already stack me hai to skip
            if (inStack[idx]) continue;

            // 3. Jab tak stack ka top > current char hai
            // aur wo top wala char aage bhi milega, to pop karo
            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                inStack[removed - 'a'] = false;
            }

            // 4. Current char ko push karo
            stack.push(c);
            inStack[idx] = true;
        }

        // 5. Stack se string banao
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }
}