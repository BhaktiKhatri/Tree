package Tree;

/*
 * 543. Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * Example: Given a binary tree 
 *           1
 *          / \
 *         2   3
 *        / \     
 *       4   5    
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * Google, Facebook
 * Easy
 */

public class DiameterOfBinaryTree {

		TreeNode root;
	/*
	 	The diameter of a tree T is the largest of the following quantities:
		* the diameter of T’s left subtree
		* the diameter of T’s right subtree
		* the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
	 */
	   public static int maxDiameter = 0;
	   
	   public static int diameterOfBinaryTree(TreeNode root) {
	      if(root == null) { 
	    	  return 0;
	      }
	      
	      height(root);
	      return maxDiameter;
	   }
	   
	   		/*
					        1
					       / \
					      2   3
					     / \     
					    4   5    
	    	*/
	   public static int height(TreeNode root) {
	        if(root == null) { 
	        	return 0;
	        }
	      
	        System.out.println("root: "+root.val);
	        
	        int leftHeight = height(root.left);
	        System.out.println("root: "+root.val+" leftHeight: "+leftHeight);
	        
	        int rightHeight = height(root.right);
	        System.out.println("root: "+root.val+" leftHeight: "+leftHeight+" rightHeight: "+rightHeight+" maxDiameter: "+maxDiameter);
	        
	        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
	        System.out.println("root: "+root.val+" maxDiameter: "+maxDiameter);
	        
	        return Math.max(leftHeight, rightHeight) + 1;
	   }
	
		public static void main(String[] args) {
				DiameterOfBinaryTree tree = new DiameterOfBinaryTree();
				
				/*
						        1
						       / \
						      2   3
						     / \     
						    4   5    
				*/
				
				tree.root = new TreeNode(1);
				tree.root.left = new TreeNode(2);
				tree.root.right = new TreeNode(3);
				tree.root.left.left = new TreeNode(4);
				tree.root.left.right = new TreeNode(5);
				
				System.out.println(diameterOfBinaryTree(tree.root));
		}

}
