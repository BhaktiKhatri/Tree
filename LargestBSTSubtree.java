package Tree;

/*
 * 333. Largest BST Subtree
 * https://leetcode.com/problems/largest-bst-subtree/description/
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 * Note: A subtree must include all of its descendants. Here's an example:
 *    10
 *    / \
 *   5  15
 *  / \   \ 
 * 1   8   7
 * The Largest BST Subtree in this case is the highlighted one. 
 * The return value is the subtree's size, which is 3.
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
 * Explanation from: https://www.youtube.com/watch?v=4fiDs7CCxkc
 * Code from: https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LargestBSTInBinaryTree.java
 * Microsoft
 * Medium
 */



/**
 * Object of this class holds information which child passes back to parent node
 */
class MinMax {
    int min;
    int max;
    boolean isBST;
    int size ;
    
    MinMax() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        isBST = true;
        size = 0;
    }
}

public class LargestBSTSubtree {

	TreeNode root;
	
	public static int largestBST(TreeNode root) {
        MinMax m = largest(root);
        return m.size;
    }
    
	public static MinMax largest(TreeNode root) {
		//if root is null return min as Integer.MAX and max as Integer.MIN 
        if(root == null) {
            return new MinMax();
        }

        System.out.println("root: "+root.val);
        
        //postorder traversal of tree. First visit left and right then use information of left and right to calculate largest BST.
        MinMax leftMinMax = largest(root.left);
        System.out.println("leftMinMax.isBST: "+leftMinMax.isBST+" leftMinMax.size: "+leftMinMax.size+" leftMinMax.min: "+leftMinMax.min+" leftMinMax.max: "+leftMinMax.max);
        
        MinMax rightMinMax = largest(root.right);
        System.out.println("rightMinMax.isBST: "+rightMinMax.isBST+" rightMinMax.size: "+rightMinMax.size+" rightMinMax.min: "+rightMinMax.min+" rightMinMax.max: "+rightMinMax.max);
        
        MinMax m = new MinMax();

        //if either of left or right subtree says its not BST or the data
        //of this node is not greater/equal than max of left and less than min of right
        //then subtree with this node as root will not be BST. 
        //Return false and max size of left and right subtree to parent
        if(leftMinMax.isBST == false || rightMinMax.isBST == false || (leftMinMax.max > root.val || rightMinMax.min <= root.val)) {
            m.isBST = false;
            m.size = Math.max(leftMinMax.size, rightMinMax.size);
            return m;
        }
        
        //if we reach this point means subtree with this node as root is BST.
        //Set isBST as true. Then set size as size of left + size of right + 1.
        //Set min and max to be returned to parent.
        m.isBST = true;
        m.size = leftMinMax.size + rightMinMax.size + 1;
     
        //if root.left is null then set root.data as min else
        //take min of left side as min
        m.min = root.left != null ? leftMinMax.min : root.val;
  
        //if root.right is null then set root.data as max else
        //take max of right side as max.
        m.max = root.right != null ? rightMinMax.max : root.val;
   
        return m;
    }
	
	public static void main(String[] args) {
		LargestBSTSubtree tree = new LargestBSTSubtree();
		
		tree.root = new TreeNode(25);
		tree.root.left = new TreeNode(18);
		tree.root.left.left = new TreeNode(19);
		tree.root.left.left.right = new TreeNode(15);
		tree.root.left.right = new TreeNode(20);
		tree.root.left.right.left = new TreeNode(18);
		tree.root.left.right.right = new TreeNode(25);
		tree.root.right = new TreeNode(50);
		tree.root.right.left = new TreeNode(35);
		tree.root.right.left.left = new TreeNode(20);
		tree.root.right.left.right = new TreeNode(40);
		tree.root.right.left.left.right = new TreeNode(25);
		tree.root.right.right = new TreeNode(60);
		tree.root.right.right.left = new TreeNode(55);
		tree.root.right.right.right = new TreeNode(70);
		
		System.out.println(largestBST(tree.root));
	}

}
