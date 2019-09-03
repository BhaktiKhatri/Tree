package Tree;

/*
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Note: A leaf is a node with no children.
 * Example: Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 * Uber, LinkedIn, Apple, Yahoo
 * Easy
 */

public class MaximumDepthOfBinaryTree {
	TreeNode root;

	/*
	 		    3
			   / \
			  9  20
			    /  \
			   15   7
	 */
	public static int maxDepth(TreeNode root) {
         if(root == null) {
			 return 0;
         }
         System.out.println("root: "+root.val);
         
         int leftDepth = maxDepth(root.left);
         System.out.println("leftDepth: "+leftDepth);
         
		 int rightDepth = maxDepth(root.right);
		 System.out.println("rightDepth: "+rightDepth);
		 
		 int maxDepth = 1 + Math.max(leftDepth, rightDepth);
		 System.out.println("maxDepth: "+maxDepth);
		 
         return maxDepth;
   }
	
	public static void main(String[] args) {
		MaximumDepthOfBinaryTree tree = new MaximumDepthOfBinaryTree();
		
		tree.root = new TreeNode(3);
		tree.root.left = new TreeNode(9);
		tree.root.right = new TreeNode(20);
		tree.root.right.left = new TreeNode(15);
		tree.root.right.right = new TreeNode(7);
		
		System.out.println(maxDepth(tree.root));
	}

}
