package Tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 545. Boundary of Binary Tree
 * https://leetcode.com/problems/boundary-of-binary-tree/description/
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and 
 * right boundary in order without duplicate nodes. Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the 
 * path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. 
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 * Example 1: Input:
 *  1
 *   \
 *    2
 *   / \
 *  3   4
 *
 * Ouput: [1, 3, 4, 2]
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2]
 * Example 2: Input:
 *	    ____1_____
 *	   /          \
 *	  2            3
 *	 / \          / 
 *	4   5        6   
 *	   / \      / \
 *	  7   8    9  10  
 *	       
 *	Ouput:
 *	[1,2,4,7,8,9,10,6,3]
 *	
 *	Explanation:
 *	The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 *	The leaves are node 4,7,8,9,10.
 *	The right boundary are node 1,3,6,10. (10 is the right-most node).
 *	So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3]
 * Explanation and Code from: https://leetcode.com/problems/boundary-of-binary-tree/discuss/101280/Java(12ms)-left-boundary-left-leaves-right-leaves-right-boundary
 * Google, Amazon
 * Medium
 */

public class BoundaryOfBinaryTree {

	public static List<Integer> nodes = new ArrayList<>(1000);
	TreeNode root;
	
	public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
	    if(root == null) { 
	    	return nodes;
	    }
	    
	    nodes.add(root.val);
	    System.out.println("root nodes: "+nodes);
	    
	    leftBoundary(root.left);
	    System.out.println("leftBoundary nodes: "+nodes);
	    
	    leaves(root.left);
	    leaves(root.right);
	    System.out.println("leaves nodes: "+nodes);
	    
	    rightBoundary(root.right);
	    System.out.println("rightBoundary nodes: "+nodes);
	    
	    return nodes;
	}
	
	public static void leftBoundary(TreeNode root) {
	    if(root == null || (root.left == null && root.right == null)) 	//skip leaves
	    	return;
	    
	    System.out.println("root: "+root.val);
	    nodes.add(root.val);
	    System.out.println("nodes: "+nodes);
	    
	    if(root.left == null) 
	    	leftBoundary(root.right);
	    else 
	    	leftBoundary(root.left);
	}
	
	public static void rightBoundary(TreeNode root) {
	    if(root == null || (root.right == null && root.left == null)) 		//skip leaves
	    	return;
	    
	    System.out.println("root: "+root.val);
	    
	    if(root.right == null)
	    	rightBoundary(root.left);
	    else 
	    	rightBoundary(root.right);
	    
	    nodes.add(root.val); 				// add after child visit(reverse)
	    System.out.println("nodes: "+nodes);
	}
	
	public static void leaves(TreeNode root) {
	    if(root == null) 
	    	return;
	    
	    System.out.println("root: "+root.val);
	    
	    if(root.left == null && root.right == null) {
	        nodes.add(root.val);
	        return;
	    }
	    System.out.println("nodes: "+nodes);
	    
	    leaves(root.left);
	    leaves(root.right);
	}
	
	public static void main(String[] args) {
		BoundaryOfBinaryTree tree = new BoundaryOfBinaryTree();
		
		/*
		 		    ____1_____
				   /          \
				  2            3
				 / \          / \
				4   5        6   14
				   / \      / \
				  7   8    9  10  
		 				  / \	
		 				 11  12  
		 	
		 */
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.left.left = new TreeNode(15);
		tree.root.left.left.left.left = new TreeNode(16);
		tree.root.left.right = new TreeNode(5);
		tree.root.left.right.left = new TreeNode(7);
		tree.root.left.right.right = new TreeNode(8);
		tree.root.right = new TreeNode(3);
		//tree.root.right.right = new TreeNode(14);
		tree.root.right.left = new TreeNode(6);
		//tree.root.right.left.right = new TreeNode(10);
		//tree.root.right.left.right.right = new TreeNode(15);
		tree.root.right.left.left = new TreeNode(9);
		tree.root.right.left.left.right = new TreeNode(12);
		tree.root.right.left.left.left = new TreeNode(11);
		//tree.root.right.left.right = new TreeNode(10);

		
		/*
		 		  1
				   \
				    2
				   / \
				  3   4
		 */
		
//		tree.root = new TreeNode(1);
//		tree.root.right = new TreeNode(2);
//		tree.root.right.left = new TreeNode(3);
//		tree.root.right.right = new TreeNode(4);
		
		
		System.out.println(boundaryOfBinaryTree(tree.root));
	}

}
