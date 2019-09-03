package Tree;

/*
 * 298. Binary Tree Longest Consecutive Sequence
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 * For example,
 *   1
 *    \
 *     3
 *    / \
 *   2   4
 *        \
 *         5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *   2
 *    \
 *     3
 *    / 
 *   2    
 *  / 
 * 1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 * Explanation and Code from: #Approach1 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/solution/
 * Time complexity : O(n); The time complexity is the same as an in-order traversal of a binary tree with n nodes.
 * Space complexity : O(n); The extra space comes from implicit stack space due to recursion. For a skewed binary tree, the recursion could go up to n
 * levels deep.
 * Google
 * Medium
 */

public class BinaryTreeLongestConsecutiveSequence {

	TreeNode root;
	
	/*
	 		   1
			    \
			     3
			    / \
			   2   4
			        \
			         5
	 */
	public static int longestConsecutive(TreeNode root) {
	    return dfs(root, null, 0);
	}

	public static int dfs(TreeNode node, TreeNode parent, int length) {
	    if(node == null) {
	    	return 0;
	    }
	    
	    System.out.println("node: "+node.val+" length: "+length+" parent: "+parent);
	    
	    if(parent != null && node.val == parent.val + 1) {
	    	length = length + 1;
	    }
	    else {
	    	length = 1;
	    }
	    
	    int leftLength = dfs(node.left, node, length);
	    int rightLength = dfs(node.right, node, length);
	    
	    System.out.println("length: "+length+" leftLength: "+leftLength+" rightLength: "+rightLength+" node: "+node.val);
	    
	    return Math.max(length, Math.max(leftLength, rightLength));
	}
	
	public static void main(String[] args) {
		BinaryTreeLongestConsecutiveSequence tree = new BinaryTreeLongestConsecutiveSequence();
		
		tree.root = new TreeNode(1);
		tree.root.right = new TreeNode(3);
		tree.root.right.left = new TreeNode(2);
		tree.root.right.right = new TreeNode(4);
		tree.root.right.right.right = new TreeNode(5);
		
		System.out.println(longestConsecutive(tree.root));
	}

}
