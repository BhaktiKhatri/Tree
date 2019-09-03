package Tree;

/*
 * 814. Binary Tree Pruning
 * https://leetcode.com/problems/binary-tree-pruning/description/
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 * Example 1: Input: [1,null,0,0,1]; Output: [1,null,0,null,1]
 * Explanation: Only the red nodes satisfy the property "every subtree not containing a 1". The diagram on the right represents the answer.
 * Example 2: Input: [1,0,1,0,0,0,1]; Output: [1,null,1,null,1]
 * Example 3: Input: [1,1,0,1,1,0,1,0]; Output: [1,1,0,1,1,null,1]
 * Note: The binary tree will have at most 100 nodes. The value of each node will only be 0 or 1.
 * Explanation and Code from: Approach #1 https://leetcode.com/problems/binary-tree-pruning/solution/
 * Time Complexity: O(N), where N is the number of nodes in the tree. We process each node once.
 * Space Complexity: O(H), where H is the height of the tree. This represents the size of the implicit call stack in our recursion.
 * Hulu
 * Medium
 */

public class BinaryTreePruning {
	
	TreeNode root;

	
	public static TreeNode pruneTree(TreeNode root) {
		System.out.println("root: "+root.val);
		
		if(root != null && root.val == 0) {
			return null;
		}
		
        if(containsOne(root)) {
        	return root;
        }
        else {
        	return null;
        }
    }

	/*
				 1
			  /    \
			  0       1
			/  \     / \	
			0   0   0   1
	 */
    public static boolean containsOne(TreeNode node) {
        if(node == null) { 
        	return false;
        }
        
        System.out.println("node: "+node.val);
        
        boolean a1 = containsOne(node.left);
        boolean a2 = containsOne(node.right);
        
        System.out.println("a1: "+a1+" a2: "+a2);
        
        if(!a1) {
        	node.left = null;
        }
        
        if(!a2) { 
        	node.right = null;
        }
        
        System.out.println("node: "+node.val+" a1: "+a1+" a2: "+a2);

        return node.val == 1 || a1 || a2;
    }
	
	public static void main(String[] args) {
		BinaryTreePruning tree = new BinaryTreePruning();
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(0);
		tree.root.left.left = new TreeNode(0);
		tree.root.left.right = new TreeNode(0);
		tree.root.right = new TreeNode(1);
		tree.root.right.left = new TreeNode(0);
		tree.root.right.right = new TreeNode(1);
		
		System.out.println(pruneTree(tree.root).val);
	}

}
