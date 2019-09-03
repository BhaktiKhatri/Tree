package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * 144. Binary Tree Preorder Traversal
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example: Given binary tree [1,null,2,3],
 *   1
 *    \
 *     2
 *    /
 *   3
 * return [1,2,3]
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Explanation and Code from: https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45266/Accepted-iterative-solution-in-Java-using-stack
 * I think the time complexity is O(n) where n is the number of all nodes, as all nodes are traversed. But I’m not sure about the space complexity.
 * It seems to be proportional to the height of the tree. The worst case would be O(n) if there are only left or right children, the best case would be
 * O(log n) if the tree is well balanced.
 * Since if the tree is balanced, the space complexity is O(h), not O(N). Thus, in general, the space complexity should be O(h)
 * Medium 
 */

public class BinaryTreePreorderTraversal {
	
	TreeNode root;
	
	/*
			   20						
			  /  \
			 8    22
			/ \
			4   12
			   /  \
			  10   14
		
			20-8-4-12-10-14-22
	 */
	//Note that in this solution only right children are stored to stack
	public static List<Integer> preorderTraversal(TreeNode node) {
		List<Integer> list = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		while(node != null) {
			System.out.println("node: "+node.val);
			
			list.add(node.val);			//add root directly to the list and in next round its left node will be added
			System.out.println("list: "+list);
		
			if(node.right != null) {
				System.out.println("node.right: "+node.right.val);
				stack.push(node.right);		//push only the right node of the root to stack so when the left subtree has reached its end i.e Root and left is done, 
			}								//right will be popped from the stack and added to the list
			
			node = node.left;				//after root is added to list, root.left will be added
			
			if(node == null && !stack.isEmpty()) {	//when left subtree has reached its end, traverse and add right root to list
				node = stack.pop();
			}
			
			if(node != null)
				System.out.println("node: "+node.val+" stack: "+stack);
		}
	    return list;
	}
	
	public static void main(String[] args) {
		BinaryTreePreorderTraversal tree = new BinaryTreePreorderTraversal();

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
		
		System.out.println(preorderTraversal(tree.root));
	}

}
