package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 * Given a binary tree, return the inorder traversal of its nodes' values. Note: Recursive solution is trivial, could you do it iteratively?
 * For example: Given binary tree [1,null,2,3],
 *   1
 *    \
 *     2
 *    /
 *   3
 * return [1,3,2]
 * Explanation and Code from: https://leetcode.com/problems/binary-tree-inorder-traversal/discuss/31213/Iterative-solution-in-Java-simple-and-readable
 * https://leetcode.com/problems/binary-tree-inorder-traversal/solution/
 * Time complexity should be O(n), cause we only add each once into stack and pop it once, so it should be O(2n), thus O(n), 
 * space complexity should be O(h), cause the most number in the stack should be the height of the tree.
 * Microsoft offer I have for Software Development Engineer full-time role before my graduation day! yaaaaaaaaaaaaaaaaaay!!!!!
 * Medium
 */

public class BinaryTreeInorderTraversal {

	TreeNode root;

	/*
		   20						
		  /  \
		 8    22
		/ \
		4   12
	   	   /  \
		  10   14
	
		4-8-10-12-14-20-22
	*/
	
	//add root and left only to stack
	public static List<Integer> inorderTraversal(TreeNode root) {
		System.out.println("root: "+root.val);
		
	    List<Integer> list = new ArrayList<Integer>();
	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    TreeNode cur = root;
	    
	    while(cur!=null || !stack.empty()) {
	    	System.out.println("cur: "+cur+" stack: "+stack);
	    	
	        while(cur != null) {
	        	System.out.println("cur: "+cur.val+" stack: "+stack);
	        	
	            stack.add(cur);
	            cur = cur.left;
	        }
	        cur = stack.pop();
	        System.out.println("cur: "+cur.val+" stack: "+stack);
	        
	        list.add(cur.val);
	        System.out.println("list: "+list);
	        
	        cur = cur.right;
	    }
	    return list;
	}
	
	public static void main(String[] args) {
		BinaryTreeInorderTraversal tree = new BinaryTreeInorderTraversal();

		/*
						   20						
						  /  \
						 8    22
						/ \
					   4   12
					   	  /  \
					   	 10   14
		*/
		
		tree.root = new TreeNode(20);
		tree.root.left = new TreeNode(8);
		tree.root.left.left = new TreeNode(4);
		tree.root.right = new TreeNode(22);
		tree.root.left.right = new TreeNode(12);
		tree.root.left.right.right = new TreeNode(14);
		tree.root.left.right.left = new TreeNode(10);
		
		System.out.println(inorderTraversal(tree.root));

	}

}
