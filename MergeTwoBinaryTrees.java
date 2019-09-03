package Tree;

import java.util.Stack;

/*
 * 617. Merge Two Binary Trees
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 * Example 1: Input: 
 * 	Tree 1                     Tree 2                  
 *           1                         2                             
 *          / \                       / \                            
 *         3   2                     1   3                        
 *        /                           \   \                      
 *       5                             4   7                  
 * Output: 
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \ 
 * 	 5   4   7
 * Note: The merging process must start from the root nodes of both trees.
 * Explanation and Code from: Approach #2 Iterative Method https://leetcode.com/problems/merge-two-binary-trees/solution/
 * Time complexity: O(n), We traverse over a total of n nodes. Here, n refers to the smaller of the number of nodes in the two trees
 * Space complexity: O(n), The depth of stack can grow upto n in case of a skewed tree
 * Amazon
 * Easy
 */

public class MergeTwoBinaryTrees {
	
	TreeNode root;
	
	/*
	 In the current approach, we again traverse the two trees, but this time we make use of a stack to do
	 so instead of making use of recursion. Each entry in the stack strores data in the form 
	 [node_{tree1}, node_{tree2}]. Here, node_{tree1} and node_{tree2} are the nodes of the first tree
	 and the second tree respectively.
	 We start off by pushing the root nodes of both the trees onto the stack. Then, at every step, 
	 we remove a node pair from the top of the stack. For every node pair removed, we add the values
	 corresponding to the two nodes and update the value of the corresponding node in the first tree.
	 Then, if the left child of the first tree exists, we push the left child(pair) of both the trees
	 onto the stack. If the left child of the first tree doesn't exist, we append the left child(subtree)
	 of the second tree to the current node of the first tree. We do the same for the right child pair as
	 well.
	 If, at any step, both the current nodes are null, we continue with popping the next nodes from the stack.
	 The following animation depicts the process.
	  
	 			Tree 1                     Tree 2                  
		          1                         2                             
		         / \                       / \                            
		        3   2                     1   3                        
		       /                           \   \                      
		      5                             4   7                 
	 */
	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) {
            return t2;
        }
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});
        
        while(!stack.isEmpty()) {
            
        	TreeNode[] t = stack.pop();
            if(t[0] == null || t[1] == null) {
                continue;
            }
            System.out.println("t[0].val: "+t[0].val+" t[1].val: "+t[1].val);
            
            t[0].val = t[0].val + t[1].val;
            
            if(t[0].left == null) {
                t[0].left = t[1].left;
            } 
            else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            
            if(t[0].right == null) {
                t[0].right = t[1].right;
            }
            else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }

	public static void main(String[] args) {
		/*
					Tree 1                     Tree 2                  
		          1                         2                             
		         / \                       / \                            
		        3   2                     1   3                        
		       /                           \   \                      
		      5                             4   7                 
		 */
		
		MergeTwoBinaryTrees tree1 = new MergeTwoBinaryTrees();
		tree1.root = new TreeNode(1);
		tree1.root.left = new TreeNode(3);
		tree1.root.right = new TreeNode(2);
		tree1.root.left.left = new TreeNode(5);
		
		MergeTwoBinaryTrees tree2 = new MergeTwoBinaryTrees();
		tree2.root = new TreeNode(2);
		tree2.root.left = new TreeNode(1);
		tree2.root.left.right = new TreeNode(4);
		tree2.root.right = new TreeNode(3);
		tree2.root.right.right = new TreeNode(7);

		System.out.println(mergeTrees(tree1.root, tree2.root));
	}

}
