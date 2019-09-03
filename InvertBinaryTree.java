package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/description/
 * Invert a binary tree. 
 * Example: Input:
 * 
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 
 * Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * Explanation and Code from: @jmnarloch https://leetcode.com/problems/invert-binary-tree/discuss/62707/Straightforward-DFS-recursive-iterative-BFS-solutions
 * Google
 * Easy
 */

public class InvertBinaryTree {
	
	TreeNode root;
	
	/*
	 		     4
			   /   \
			  2     7
			 / \   / \
			1   3 6   9
		Finally we can easly convert the above solution to BFS - or so called level order traversal.
	 */
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        System.out.println("root: "+root.val);
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
        	System.out.println("queue: "+queue);
            
        	TreeNode node = queue.poll();
        	System.out.println("node: "+node.val);
        	
        	TreeNode left = node.left;
            //System.out.println("left: "+left.val);
            
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
            	System.out.println("left: "+node.left.val);
                queue.offer(node.left);
            }
            
            if(node.right != null) {
            	System.out.println("right: "+node.right.val);
                queue.offer(node.right);
            }
        }
        System.out.println("root: "+root.val);
        return root;
    }
	
	public static void main(String[] args) {
		InvertBinaryTree tree = new InvertBinaryTree();
			
		/*
		 			     4
					   /   \
					  2     7
					 / \   / \
					1   3 6   9
		 */

		tree.root = new TreeNode(4);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(1);
		tree.root.left.right = new TreeNode(3);
		tree.root.right = new TreeNode(7);
		tree.root.right.left = new TreeNode(6);
		tree.root.right.right = new TreeNode(9);
	
		System.out.println(invertTree(tree.root).val);
		
	}
}