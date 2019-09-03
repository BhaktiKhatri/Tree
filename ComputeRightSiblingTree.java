package Tree;

/*
 * EPI: 9.16 Compute the right sibling tree
 * Page 144
 * WAP that takes a perfect binary tree, and sets each node's level-next field to the node on its right, if one exists
 */

/**
 * 116. Populating Next Right Pointers in Each Node
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 * Given a binary tree
 * struct TreeLinkNode {
 *	  TreeLinkNode *left;
 *	  TreeLinkNode *right;
 *	  TreeLinkNode *next;
 *	}
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note: You may only use constant extra space. Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * Example: Given the following perfect binary tree,
 *
 *	     1
 *	   /  \
 *	  2    3
 *	 / \  / \
 *	4  5  6  7
 * After calling your function, the tree should look like:
 *     1 -> NULL
 *   /  \
 *  2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 * Microsoft
 * Medium
 */

public class ComputeRightSiblingTree {
	
	BinaryTreeNode root;

	public static void constructRightSibling(BinaryTreeNode root) {
		if(root == null)
			return;
		
		BinaryTreeNode leftStart = root;
		System.out.println("leftStart: "+leftStart.data);
		
		while(leftStart != null && leftStart.left != null) {
			System.out.println("leftStart: "+leftStart.data+" leftStart.left: "+leftStart.left.data);
			populateLowerLevelNextField(leftStart);
			leftStart = leftStart.left;
		}
	}
	
	/*
			        1
				  /  \
				 2    3
				/ \  / \
				4  5  6  7
	 */

	public static void populateLowerLevelNextField(BinaryTreeNode startNode) {
		BinaryTreeNode curr = startNode;
		
		while(curr != null) {
		
			System.out.println("curr: "+curr.data+" curr.right: "+curr.right.data);
			curr.left.next = curr.right;
			
			if(curr.next != null && curr.next.left != null) {
				System.out.println("curr.next: "+curr.next.data+" curr.next.left: "+curr.next.left);
				curr.right.next = curr.next.left;
			}
			
			curr = curr.next;
		}
	}
	
	public static void main(String[] args) {
		ComputeRightSiblingTree tree = new ComputeRightSiblingTree();		
		/*
		 				     1
						   /  \
						  2    3
						 / \  / \
						4  5  6  7
		 */
		
		tree.root = new BinaryTreeNode(1);
		tree.root.left = new BinaryTreeNode(2);
		tree.root.right = new BinaryTreeNode(3);
		tree.root.left.left = new BinaryTreeNode(4);
		tree.root.left.right = new BinaryTreeNode(5);
		tree.root.right.left = new BinaryTreeNode(6);
		tree.root.right.right = new BinaryTreeNode(7);
		
		constructRightSibling(tree.root);
		System.out.println();
	}

}
