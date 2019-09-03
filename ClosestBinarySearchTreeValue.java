package Tree;

/*
 * 270. Closest Binary Search Tree Value
 * https://leetcode.com/problems/closest-binary-search-tree-value/description/
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note: Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * Example: Input: root = [4,2,5,1,3], target = 3.714286
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 * Output: 4
 * Microsoft, Google, Snapchat
 * Easy
 */

public class ClosestBinarySearchTreeValue {
	
	TreeNode root;
	
	/*
	 			    4
				   / \
				  2   5
				 / \
				1   3
				
				target = 3.714286
	 */
	public static int closestValue(TreeNode root, double target) {
	    System.out.println("root: "+root.val+" target: "+target);
		
		int a = root.val;
		System.out.println("a: "+a+" target: "+target);
		
	    TreeNode kid = target < a ? root.left : root.right;
	    
	    if(kid == null) { 
	    	return a;
	    }
	    
	    int b = closestValue(kid, target);
	    System.out.println("b: "+b);
	    
	    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
	}
	
	 /*
	  	Refer this
					    4
					   / \
					  2   5
					 / \
					1   3
				
				target = 4.714286
	 */
	public static int closestValue1(TreeNode root, double target) {
		int closest = root.val;
		System.out.println("root: "+root.val+" closest: "+closest);
		
	    while(root != null) {
	    	System.out.println("root: "+root.val+" Math.abs(closest - target): "+Math.abs(closest - target)+" Math.abs(root.val - target): "+Math.abs(root.val - target));
	    	
	    	if(Math.abs(closest - target) >= Math.abs(root.val - target)) {
	            closest = root.val;
	        }
	        
	    	System.out.println("closest: "+closest+" target: "+target+" root: "+root.val);
	    	root = target < root.val ? root.left : root.right;
	    }
	    System.out.println("closest: "+closest);
	    
	    return closest;
	}
	
	public static void main(String[] args) {
		ClosestBinarySearchTreeValue tree = new ClosestBinarySearchTreeValue();
		
		tree.root = new TreeNode(4);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(1);
		tree.root.left.right = new TreeNode(3);
		tree.root.right = new TreeNode(5);
		
		double target = 4.714286;
		System.out.println(closestValue1(tree.root, target));
	}	

}
