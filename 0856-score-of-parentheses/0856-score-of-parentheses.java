class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                depth++;
            } else {
                depth--;
                // If it forms a core "()", add its value based on nesting depth
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // equivalent to score += Math.pow(2, depth)
                }
            }
        }

        return score;
    }
}