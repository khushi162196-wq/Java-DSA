class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        
        int n = prices.length;
        
        // hold: max profit if we hold a stock on day i
        // sold: max profit if we sold stock on day i 
        // rest: max profit if we are in cooldown/rest on day i
        int hold = -prices[0]; // buy on day 0
        int sold = 0;
        int rest = 0;
        
        for (int i = 1; i < n; i++) {
            int prevHold = hold;
            int prevSold = sold;
            int prevRest = rest;
            
            // 1. hold: either keep holding, or buy today from rest state
            hold = Math.max(prevHold, prevRest - prices[i]);
            
            // 2. sold: we must have been holding and sell today
            sold = prevHold + prices[i];
            
            // 3. rest: either keep resting, or enter cooldown after selling
            rest = Math.max(prevRest, prevSold);
        }
        
        return Math.max(sold, rest);
    }
}