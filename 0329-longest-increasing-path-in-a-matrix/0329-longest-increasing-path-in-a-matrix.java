class Solution {
    int m, n;
    int[][] dp;
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // right, left, down, up

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m][n];

        int maxLen = 0;
        // Har cell se DFS start karo
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j));
            }
        }
        return maxLen;
    }

    private int dfs(int[][] matrix, int i, int j) {
        // Agar pehle calculate kar chuke hain to wapas return karo
        if (dp[i][j]!= 0) return dp[i][j];

        int max = 1; // khud ko count karo
        // 4 direction check karo
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];

            // Valid hai aur bada hai to jao
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                max = Math.max(max, 1 + dfs(matrix, x, y));
            }
        }

        dp[i][j] = max; // memoize kar do
        return max;
    }
}