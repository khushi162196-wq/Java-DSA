import java.util.*;

class Solution {

    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0, rightRemove = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove == 0)
                    rightRemove++;
                else
                    leftRemove--;
            }
        }

        backtrack(s, 0, leftRemove, rightRemove, 0, new StringBuilder());

        return new ArrayList<>(ans);
    }

    private void backtrack(String s, int index, int leftRemove,
                           int rightRemove, int open,
                           StringBuilder path) {

        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0 && open == 0) {
                ans.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = path.length();

        if (c == '(') {

            if (leftRemove > 0)
                backtrack(s, index + 1, leftRemove - 1, rightRemove, open, path);

            path.append(c);
            backtrack(s, index + 1, leftRemove, rightRemove, open + 1, path);
            path.setLength(len);

        } else if (c == ')') {

            if (rightRemove > 0)
                backtrack(s, index + 1, leftRemove, rightRemove - 1, open, path);

            if (open > 0) {
                path.append(c);
                backtrack(s, index + 1, leftRemove, rightRemove, open - 1, path);
                path.setLength(len);
            }

        } else {

            path.append(c);
            backtrack(s, index + 1, leftRemove, rightRemove, open, path);
            path.setLength(len);
        }
    }
}