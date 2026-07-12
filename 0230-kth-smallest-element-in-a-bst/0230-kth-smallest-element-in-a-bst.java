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

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            // sabse left tak jao
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // node process karo
            curr = stack.pop();
            k--;
            if(k == 0) return curr.val;

            // right me jao
            curr = curr.right;
        }
        return -1; // should not reach
    }
}