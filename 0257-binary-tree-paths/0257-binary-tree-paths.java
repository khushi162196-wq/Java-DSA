/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;

// TreeNode class LeetCode me pehle se hoti hai
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution { // Solutions nahi, Solution
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, "", ans); // " nahi, "" empty string
        return ans;
    }
    
    private void dfs(TreeNode node, String path, List<String> ans) { // perivate nahi private
        if (node == null) {
            return;
        }
        
        // Path banao
        if (path.length() == 0) {
            path = String.valueOf(node.val); // valueOf capital V
        } else {
            path = path + "->" + node.val;
        }
        
        // Leaf node hai toh add kar do
        if (node.left == null && node.right == null) {
            ans.add(path);
            return;
        }
        
        // Yaha return nahi lagana tha! Is wajah se child call hi nahi ho raha tha
        dfs(node.left, path, ans);
        dfs(node.right, path, ans);
    }
}