package Tree;

/*
 * 450. Delete Node in a BST
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages: Search for a node to remove. If the node is found, delete the node
 * Note: Time complexity should be O(height of tree)
 * Example: root = [5,3,6,2,4,null,7]; key = 3
 * 
 *    5
 *   / \
 *  3   6
 * / \   \
 * 2   4   7
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *	 	 5
 *   	/ \
 *     4    6
 * 	   /     \
 *	   2      7
 * Another valid answer is [5,2,6,null,4,null,7].
 *
 *	    5
 *	   / \
 *	  2   6
 *	   \   \
 *	    4   7
 * Explanation and Code from: @lantian9090@gmail.com https://leetcode.com/problems/delete-node-in-a-bst/discuss/93296/Recursive-Easy-to-Understand-Java-Solution
 * Uber
 * Medium
 */

public class DeleteNodeInBST {

	TreeNode root;
	
	 /*
			 	5
			   / \
			  3   8
			 / \  /\
			2   4 7 10
					/
					9
			key=3
	 */
	
	public static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
        	return null;
        }
        System.out.println("root: "+root.val+" key: "+key);
        
        if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } 
        else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        else {
        	System.out.println("root: "+root.val+" key: "+key);
            if(root.left == null) 
            	return root.right;
            
            if(root.right == null) 
            	return root.left;
            
            TreeNode rightSmallest = root.right;
            System.out.println("rightSmallest: "+rightSmallest.val);
            
            while(rightSmallest.left != null) { 
            	rightSmallest = rightSmallest.left;
            }
            rightSmallest.left = root.left;
            
            return root.right;
        }
        return root;
    }
	
	public static void main(String[] args) {
		/*
		     	5
			   / \
			  3   8
			 / \  /\
			2  4  7 10
					/
					9	
		*/
	
		DeleteNodeInBST tree = new DeleteNodeInBST();
		
		tree.root = new TreeNode(5);
		tree.root.left = new TreeNode(3);
		tree.root.left.left = new TreeNode(2);
		tree.root.left.right = new TreeNode(4);
		tree.root.right = new TreeNode(8);
		tree.root.right.left = new TreeNode(7);
		tree.root.right.right = new TreeNode(10);
		tree.root.right.right.left = new TreeNode(9);
		int key = 2;
		
		System.out.println(deleteNode(tree.root, key).val);
	}

}
