class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

        // Step 1: Har cell ke liye next state calculate karo
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int liveNeighbors = 0;

                // 8 neighbors count karo. Sirf 1st bit dekho
                for(int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if(ni >= 0 && ni < m && nj >= 0 && nj < n) {
                        liveNeighbors += board[ni][nj] & 1; // &1 = current state
                    }
                }

                // Rules apply karo. Next state ko 2nd bit me daalo
                if(board[i][j] == 1) { // currently live
                    if(liveNeighbors == 2 || liveNeighbors == 3) {
                        board[i][j] |= 2; // next state = 1. 10 binary
                    }
                } else { // currently dead
                    if(liveNeighbors == 3) {
                        board[i][j] |= 2; // next state = 1
                    }
                }
            }
        }

        // Step 2: 2nd bit ko 1st bit me shift kar do
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] >>= 1; // next state ab current ban gaya
            }
        }
    }
}