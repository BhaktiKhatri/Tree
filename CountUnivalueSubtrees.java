package Tree;

/*
 * 250. Count Univalue Subtrees
 * https://leetcode.com/problems/count-univalue-subtrees/description/
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * For example: Given binary tree,
 *              5
 *             / \
 *            1   5
 *           / \   \
 *          5   5   5
 * return 4
 * Explanation and Code from: @turingfly https://leetcode.com/problems/count-univalue-subtrees/discuss/67573/My-Concise-JAVA-Solution
 * Time Complexity - O(n), Space Complexity - O(n)
 */

public class CountUnivalueSubtrees {

	public static int res = 0;
	TreeNode root;
	
	public static int countUnivalSubtrees(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }
    
	public static boolean helper(TreeNode node) {
        if(node == null) {
            return true;
        }
        
        System.out.println("node: "+node.val+" res: "+res);
        
        boolean left = helper(node.left);
        boolean right = helper(node.right);
        
        System.out.println("node: "+node.val+" left: "+left+" right: "+right+" res: "+res);
        
        if(left && right) {
            if ((node.left != null && node.val != node.left.val) || (node.right != null && node.val != node.right.val)) {
            	return false;
            }
            res++;
            return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		CountUnivalueSubtrees tree = new CountUnivalueSubtrees();
		
		tree.root = new TreeNode(5);
		tree.root.left = new TreeNode(1);
		tree.root.left.left = new TreeNode(5);
		tree.root.left.right = new TreeNode(5);
		tree.root.right = new TreeNode(5);
		tree.root.right.right = new TreeNode(5);
		
		System.out.println(countUnivalSubtrees(tree.root));
	}

}
