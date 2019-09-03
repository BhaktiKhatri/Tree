package Tree;

/*
 * 156. Binary Tree Upside Down
 * https://leetcode.com/problems/binary-tree-upside-down/description/
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * For example: Given a binary tree {1,2,3,4,5},
 *	    1
 *	   / \
 *	  2   3
 *	 / \
 *	4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *	   4
 *	  / \
 *	 5   2
 *	    / \
 *	   3   1  
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ
 * Explanation and Code from: https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure 
 * Time Complexity: O(h); Space complexity: O(1)
 * LinkedIn
 * Medium
 */

public class BinaryTreeUpsideDown {

	TreeNode root;
	
	public static TreeNode upsideDownBinaryTree(TreeNode root) {
	    TreeNode curr = root;
	    TreeNode next = null;
	    TreeNode temp = null;
	    TreeNode prev = null;
	    
	    System.out.println("root: "+root.val+" curr: "+curr.val);
	    
	    while(curr != null) {		//1; 2; 4
	        next = curr.left;		//2; 4; null
	        
	        // swapping nodes now, need temp to keep the previous right child
	        curr.left = temp;		//1.left = null; 2.left = 3; 4.left = 5
	        temp = curr.right;		//temp = 3 = 1.right; 2.right = 5; temp = 4.right = null
	        curr.right = prev;		//1.right = null; 2.right = prev = 1; 4.right = prev = 2
	        
	        prev = curr;			//prev = curr = 1; prev = 2; prev = 4
	        curr = next;			//curr = next = 2; 4; curr = null
	    }
	    System.out.println("prev: "+prev.val);
	    
	    return prev;
	}  
	
	public static void main(String[] args) {
		/*
		 	1
		   / \
		  2   3
		 / \
		4   5
		 */
		
		BinaryTreeUpsideDown tree = new BinaryTreeUpsideDown();
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);
		tree.root.right = new TreeNode(3);

		System.out.println(upsideDownBinaryTree(tree.root).val);
	}

}
