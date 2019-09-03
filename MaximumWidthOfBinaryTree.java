package Tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/description/
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has
 * the same structure as a full binary tree, but some nodes are null. The width of one level is defined as the length between the end-nodes
 * (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 * Explanation and Code from: @hot13399 https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/106645/C++Java-*-BFSDFS3liner-Clean-Code-With-Explanation
 * Example 1: Input: 
		           1        1       
		         /   \
		        3     2   	2 2*n-1
		       / \     \  
		      5   3     9 
		
		Output: 4
		Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
		Example 2:
		Input: 
		          1
		         /  
		        3    
		       / \       
		      5   3     
		
		Output: 2
		Explanation: The maximum width existing in the third level with the length 2 (5,3).
		Example 3:
		Input: 
		          1
		         / \
		        3   2 
		       /        
		      5      
		
		Output: 2
		Explanation: The maximum width existing in the second level with the length 2 (3,2).
		Example 4:
		Input: 
		          1
		         / \
		        3   2
		       /     \  
		      5       9 
		     /         \
		    6           7
		Output: 8
		Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
		Note: Answer will in the range of 32-bit signed integer.
 * 
 * 
 */

public class MaximumWidthOfBinaryTree {

	TreeNode root;
	/*
		 Give each binary tree node a index, just like heap:
	
	          root: i
		left:i * 2   right: i * 2 + 1
	 
	    List<Integer> lefts records the left-most index of one level.
	    Compare all nodes distance to its level left-most node.
	    
	    We know that for a perfect binary tree, if the root node is a depth of 1, then the number of nodes
	    in each layer is 2*n-1. Then the position of each node is one of [1, 2*n-1]. Assume that the position
	    of a node is i. Then the position of its left and right child nodes can be calculated directly as 2*i
	    and 2 *i+1, you can take the example test yourself.
	 
	 This property refers to 2 ^ (n-1) nodes per layer. The left child's label of a node is 2n, and the right node label is 2n + 1.
	 http://www.cnblogs.com/grandyang/p/7538821.html
	 */
    public static int maxWidth = 0;
    
    public static int widthOfBinaryTree1(TreeNode root) {
        helper(root, new ArrayList<Integer>(), 0, 0);
        return maxWidth;
    }
    
    public static void helper(TreeNode root, List<Integer> lefts, int level, int index) {
        if(root == null) 
        	return;
        System.out.println("root: "+root.val+" lefts: "+lefts+" level: "+level+" index: "+index);
        
        if(level == lefts.size()) {
           lefts.add(index); 
        }
        System.out.println("max: "+maxWidth+" lefts: "+lefts+" level: "+level+" index: "+index);
        
        maxWidth = Math.max(maxWidth, index - lefts.get(level) + 1);
        System.out.println("maxWidth: "+maxWidth);
        
        helper(root.left, lefts, level + 1, index * 2);
        helper(root.right, lefts, level + 1, index * 2 + 1);
    }
    
    public static void main(String[] args) {
		MaximumWidthOfBinaryTree tree = new MaximumWidthOfBinaryTree();
		
		/*
		 	
		           1
		         /  \
		        3    2
		       / \    \  
		      5   3    9 
		 */

		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(3);
		tree.root.left.left = new TreeNode(5);
		tree.root.left.right = new TreeNode(3);
		tree.root.right = new TreeNode(2);
		tree.root.right.right = new TreeNode(9);
		
		System.out.println(widthOfBinaryTree1(tree.root));
		
	}
}