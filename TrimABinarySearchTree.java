package Tree;

/*
 * 669. Trim a Binary Search Tree
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 * Example 1: Input: 
 *     1
 *    / \
 *   0   2
 * 
 *   L = 1
 *   R = 2
 * Output: 
 *     1
 *       \
 *        2
 * Example 2:
 * Input: 
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *   L = 1
 *   R = 3
 * Output: 
 *       3
 *      / 
 *    2   
 *   /
 * 1
 * 
 * Explanation and Code from: Approach #1: Recursion https://leetcode.com/problems/trim-a-binary-search-tree/solution/
 * Time Complexity: O(N), where N is the total number of nodes in the given tree. We visit each node at most once.
 * Space Complexity: O(N), Even though we don't explicitly use any additional memory, the call stack of our recursion could be as large as the number of nodes in the worst case.
 * Bloomberg
 * Easy 
 */

public class TrimABinarySearchTree {
	
	TreeNode root;

	public static TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) {
        	return root;
        }
        
        System.out.println("root: "+root.val+" L: "+L+" R: "+R);
        
        if(root.val > R) {
        	return trimBST(root.left, L, R);
        }
        
        if(root.val < L) { 
        	return trimBST(root.right, L, R);
        }
        
        root.left = trimBST(root.left, L, R);
        
        root.right = trimBST(root.right, L, R);
        
        return root;
    }
	
	public static void main(String[] args) {
			/*
			 		    3
					   / \
					  0   4
					   \
					    2
					   /
					  1

					  L = 1
					  R = 3
			 */

		int L = 1;
		int R = 3;
		
		TrimABinarySearchTree tree = new TrimABinarySearchTree();
		tree.root = new TreeNode(3);
		tree.root.left = new TreeNode(0);
		tree.root.right = new TreeNode(4);
		tree.root.left.right = new TreeNode(2);
		tree.root.left.right.left = new TreeNode(1);
		
		System.out.println(trimBST(tree.root, L, R));
	}

}
