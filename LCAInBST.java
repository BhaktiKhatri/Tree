package Tree;

/*
 * Compute LCA in a BST
 * Page 231: EPI 14.4
 * Time Complexity: O(h)
 */

/*
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 
 *         _______6______
 *        /              \
 *     ___2__          ___8__
 *    /      \        /      \
 *    0      _4       7       9
 *          /  \
 *          3   5
 * Example 1: Input: root, p = 2, q = 8
 * Output: 6 
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2: Input: root, p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * Microsoft, Facebook, Amazon, Twitter
 * Easy
 */

public class LCAInBST {

	/*
	 * Rule 1: If the root's key is the same as that stored at node1 or node2 then root is the LCA
	 * Rule 2: If key at node1 is smaller than the key at root, and the key at node2 is greater than key at root then root is the LCA
	 * Rule 3: If the key at node1 and node2 are smaller than that at the root, then the LCA must lie in the left subtree of the root
	 * Rule 4: If the key at node1 and node are larger than that at the root, then the LCA must lie in the right subtree of the root
	 */
	
	BSTNode root;
	
	/*
				     _______6______
				    /              \
				 ___2__           ___8__
				/      \         /      \
				0      _4       7       9
				     /  \
				     3   5
	 */
	
	public static BSTNode findLCA(BSTNode root, BSTNode node1, BSTNode node2) {
		System.out.println("root: "+root.data+" node1: "+node1.data+" node2: "+node2.data);
		
		while(root.data < node1.data || root.data > node2.data) {
			//Keep searching since root is outside of [node1,node2]
			
			System.out.println("root: "+root.data+" node1: "+node1.data+" node2: "+node2.data);
			while(root.data < node1.data) {
				root = root.right;		//LCA must be in root's right child
			}
			while(root.data > node2.data) {
				root = root.left;
			}
		}
		
		//Now, node1.data <= root.data && root.data <= node2.data or one of the node is root; so root is the LCA of node1 and node2
		return root;
	}
	
	public static void main(String[] args) {
		LCAInBST tree = new LCAInBST();
				
		/*
		 		    _______6______
			       /              \
			    ___2__          ___8__
			   /      \        /      \
			   0      _4       7       9
			         /  \
			         3   5
		 */
		
		//tree.root = new BSTNode(2);
		//tree.root.left = new BSTNode(1);
		
		tree.root = new BSTNode(6);
		tree.root.left = new BSTNode(2);
		tree.root.right = new BSTNode(8);
		tree.root.left.left = new BSTNode(0);
		tree.root.left.right = new BSTNode(4);
		tree.root.left.right.left = new BSTNode(3);
		tree.root.left.right.right = new BSTNode(5);
		tree.root.right.left = new BSTNode(7);
		tree.root.right.right = new BSTNode(9);

		//System.out.println(findLCA(tree.root, tree.root.left.left, tree.root.left.right.left).data);
		
		System.out.println(findLCA(tree.root, tree.root.left.right, tree.root.left.right.right).data);
	}
}