package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * 145. Binary Tree Postorder Traversal
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * Example: Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * Explanation and Code from: @yavinci https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
 * 
 */

public class BinaryTreePostOrderTraversal {

	TreeNode root;
	
	 	/*
						   20						
						  /  \
						 8    22
						/ \
					   4   12
					   	  /  \
					   	 10   14
	
				Left-Right-Root
				4-10-14-12-8-22-20
		*/
	
	public static List<Integer> postorderTraversal(TreeNode root) {
	    LinkedList<Integer> result = new LinkedList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    
	    System.out.println("root: "+root.val);
	    
	    while(!stack.isEmpty() || p != null) {
	    	
	        while(p != null) {
	        	System.out.println("p: "+p.val+" stack: "+stack+" result: "+result);
	            stack.push(p);
	            result.addFirst(p.val);  // Reverse the process of preorder
	            p = p.right;             // Reverse the process of preorder
	        } 
	         
	        TreeNode node = stack.pop();
	        System.out.println("node: "+node.val+" result: "+result);
	        p = node.left;           // Reverse the process of preorder
	    }
	    System.out.println("result: "+result);
	    
	    return result;
	}
	
	//Refer this: @xiaoyuz666: https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) 
        	return list;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()) {
            
        	TreeNode curr = stack.pop();
        	System.out.println("curr: "+curr.val+" list: "+list);
            
        	list.add(0,curr.val);
            
            if(curr.left!=null) {
              stack.push(curr.left);
            }
            
            if(curr.right!=null) {
               stack.push(curr.right); 
            }
        }
        System.out.println("list: "+list);
        
        return list;
    }

	public static void main(String[] args) {
		BinaryTreePostOrderTraversal tree = new BinaryTreePostOrderTraversal();

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
		
		System.out.println(postorderTraversal1(tree.root));
	}
}