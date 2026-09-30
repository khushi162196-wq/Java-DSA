class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;


        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                // Assign current depth parity to group 0 or 1, then increment depth
                ans[i] = depth % 2;
                depth++;
            } else {
                // Decrement depth first so matching closing bracket has the same parity
                depth--;
                ans[i] = depth % 2;
            }
        }

        return ans;
    }
}