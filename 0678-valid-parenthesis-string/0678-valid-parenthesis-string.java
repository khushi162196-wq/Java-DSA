class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; // Minimum possible count of unmatched '('
        int maxOpen = 0; // Maximum possible count of unmatched '('

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*' can be ')', '(', or empty
                minOpen--; // treated as ')'
                maxOpen++; // treated as '('
            }

            // More ')' than available '(' even if every '*' was '('
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot drop below 0 because '*' can simply act as empty
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // If minOpen can reach 0, all parentheses can be balanced
        return minOpen == 0;
    }
}