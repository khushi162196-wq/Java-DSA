import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        
        // 1. Build graph + degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }
        
        // 2. Add all leaves to queue. Leaves have degree 1
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        
        // 3. Trim leaves layer by layer until <= 2 nodes left
        int remaining = n;
        while (remaining > 2) {
            int size = queue.size();
            remaining -= size;
            
            for (int i = 0; i < size; i++) {
                int leaf = queue.poll();
                
                // remove leaf from its neighbor
                for (int neighbor : graph.get(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        // 4. Remaining nodes in queue are the centers
        while (!queue.isEmpty()) res.add(queue.poll());
        return res;
    }
}