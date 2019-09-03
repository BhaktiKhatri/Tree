package Tree;

/*
 * 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * Explanation and Code from: Elements of Programming Interviews-Binary Tree Page 129
 * Time Complexity: O(n); Space Complexity: O(h)
 * Microsoft, Apple, Facebook, Amazon, LinkedIn
 * Medium
 */

class Status {
	public int numTargetNodes;
	public BinaryTreeNode ancestor;
	
	public Status(int numTargetNodes, BinaryTreeNode ancestor) {
		this.numTargetNodes = numTargetNodes;
		this.ancestor = ancestor;
	}
}

public class LowestCommonAncestorInBinaryTree {

	BinaryTreeNode root;
	
	public static BinaryTreeNode LCA(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
		return LCAHelper(root, node1, node2).ancestor;
	}
	
	public static Status LCAHelper(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {
		if(root == null) {
			return new Status(0, null);
		}
		
		System.out.println("root: "+root.data+" node1: "+node1.data+" node2: "+node2.data);
		
		Status leftResult = LCAHelper(root.left, node1, node2);
		if(leftResult.numTargetNodes == 2)
			return leftResult;					//Found both nodes in the left subtree
		
		Status rightResult = LCAHelper(root.right, node1, node2);
		if(rightResult.numTargetNodes == 2)
			return rightResult;					//Found both nodes in the right subtree
		
		int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes + (root == node1 ? 1 : 0) + (root == node2 ? 1 : 0);
		
		return new Status(numTargetNodes, numTargetNodes == 2 ? root : null);
	}
	
	public static void main(String[] args) {
		
		LowestCommonAncestorInBinaryTree tree = new LowestCommonAncestorInBinaryTree();
		
		tree.root = new BinaryTreeNode(3);
		tree.root.left = new BinaryTreeNode(5);
		tree.root.right = new BinaryTreeNode(1);
		tree.root.left.left = new BinaryTreeNode(6);
		tree.root.left.right = new BinaryTreeNode(2);
		tree.root.left.right.left = new BinaryTreeNode(7);
		tree.root.left.right.right = new BinaryTreeNode(4);
		tree.root.right.left = new BinaryTreeNode(0);
		tree.root.right.right = new BinaryTreeNode(8);
		
		/*
		 		    _______3______
			       /              \
			    ___5__          ___1__
			   /      \        /      \
			   6      _2       0       8
			         /  \
			         7   4
		 */
		
		tree.root.left.parent = tree.root;
		tree.root.right.parent = tree.root;
		tree.root.left.left.parent = tree.root.left;
		tree.root.left.right.parent = tree.root.left;
		tree.root.left.right.left.parent = tree.root.left.right;
		tree.root.left.right.right.parent = tree.root.left.right;
		tree.root.right.left.parent = tree.root.right;
		tree.root.right.right.parent = tree.root.right;
		
		System.out.println(LCA(tree.root, tree.root.left.right, tree.root.left.right.right).data);	//LCA(5,6) -> 5
	}
}