class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int added = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    // Match with a previously unmatched '('
                    open--;
                } else {
                    // No '(' to match, must insert one
                    added++;
                }
            }
        }

        // Total additions = needed '(' + remaining unmatched '(' that need ')'
        return added + open;
    }
}