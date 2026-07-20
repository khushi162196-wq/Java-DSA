import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;
        
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < m; i++){
            // n size ki list bana do, 0 se fill
            res.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int oldIndex = i * n + j;
                int newIndex = (oldIndex + k) % total;
                
                int new_i = newIndex / n;
                int new_j = newIndex % n;
                
                res.get(new_i).set(new_j, grid[i][j]); // set use karo
            }
        }
        return res;
    }
}