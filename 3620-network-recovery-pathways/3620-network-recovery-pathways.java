class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Step 1: Binary search on answer
        int left = 0, right = 0;
        for (int[] e : edges) right = Math.max(right, e[2]);
        
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(edges, online, n, k, mid)) {
                ans = mid; // mid possible hai, bada try karo
                left = mid + 1;
            } else {
                right = mid - 1; // mid bada hai, chota karo
            }
        }
        return ans;
    }
    
    // Step 2: Check karo ki min-edge >= 'limit' rakh ke n-1 pahunch sakte kya
    private boolean canReach(int[][] edges, boolean[] online, int n, long k, int limit) {
        // Graph banao sirf >= limit wali edges ka
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], cost = e[2];
            if (cost >= limit && online[u] && online[v]) {
                graph[u].add(new int[]{v, cost});
            }
        }
        
        // Dijkstra for shortest path with cost <= k
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0}); // node, totalCost
        
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int)cur[0];
            long cost = cur[1];
            
            if (u == n - 1) return cost <= k;
            if (cost > dist[u]) continue;
            
            for (int[] nei : graph[u]) {
                int v = nei[0];
                long newCost = cost + nei[1];
                if (newCost < dist[v] && newCost <= k) {
                    dist[v] = newCost;
                    pq.offer(new long[]{v, newCost});
                }
            }
        }
        return false;
    }
}