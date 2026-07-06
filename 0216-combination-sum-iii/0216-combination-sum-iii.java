import java.util.*;

class Solution { // Class name Solution singular hota hai
    List<List<Integer>> ans = new ArrayList<>(); // Integer spelling + ArrayList

    public List<List<Integer>> combinationSum3(int k, int n) { // Parameter order: k, n
        backtrack(1, k, n, new ArrayList<>());
        return ans;
    }

    public void backtrack(int start, int k, int target, List<Integer> curr) {
        // Base case: target 0 ho gaya aur k numbers bhi use ho gaye
        if (target == 0 && curr.size() == k) {
            ans.add(new ArrayList<>(curr)); // new ArrayList<>(curr)
            return;
        }
        // Invalid case
        if (target < 0 || curr.size() > k) {
            return;
        }
        
        for (int i = start; i <= 9; i++) {
            curr.add(i);
            backtrack(i + 1, k, target - i, curr); // aage wale number se start
            curr.remove(curr.size() - 1); // backtrack
        }
    }

    // Testing ke liye main
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.combinationSum3(3, 7)); // [[1,2,4]]
        System.out.println(obj.combinationSum3(3, 9)); // [[1,2,6], [1,3,5], [2,3,4]]
    }
}