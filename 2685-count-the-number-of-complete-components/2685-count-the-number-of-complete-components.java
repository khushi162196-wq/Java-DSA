import java.util.*;

class Solution {
    class DSU {
        int[] parent, size;
        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        int find(int x) {
            if(parent[x]!= x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int px = find(x), py = find(y);
            if(px == py) return;
            if(size[px] < size[py]) {
                parent[px] = py;
                size[py] += size[px];
            } else {
                parent[py] = px;
                size[px] += size[py];
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);

        // Step 1: Union all edges
        for(int[] e : edges) {
            dsu.union(e[0], e[1]);
        }

        // Step 2: Har component ke nodes aur edges count karo
        Map<Integer, Integer> nodeCount = new HashMap<>();
        Map<Integer, Integer> edgeCount = new HashMap<>();

        // Nodes count
        for(int i = 0; i < n; i++) {
            int root = dsu.find(i);
            nodeCount.put(root, nodeCount.getOrDefault(root, 0) + 1);
        }

        // Edges count
        for(int[] e : edges) {
            int root = dsu.find(e[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }

        // Step 3: Check complete or not
        int ans = 0;
        for(int root : nodeCount.keySet()) {
            int nodes = nodeCount.get(root);
            int edgesInComp = edgeCount.getOrDefault(root, 0);
            int requiredEdges = nodes * (nodes - 1) / 2;

            if(edgesInComp == requiredEdges) {
                ans++;
            }
        }
        return ans;
    }
}