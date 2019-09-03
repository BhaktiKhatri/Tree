package Tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/description/
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example: Given the below binary tree and sum = 22,
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \    / \
 *        7    2  5   1
 * return [[5,4,11,2],[5,8,4,5]]
 * Explanation and Code from: @xi11 https://leetcode.com/problems/path-sum-ii/discuss/36683/DFS-with-one-LinkedList-accepted-java-solution
 * Bloomberg
 * Medium 
 */

public class PathSum2 {

	TreeNode root;
	
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>(); 
	    List<Integer> cur = new ArrayList<Integer>(); 
	    
	    pathSum(root, sum, cur, result);
	    return result;
	}

	public static void pathSum(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> result) {
	    if (root == null) {
	        return; 
	    }
	    
	    System.out.println("root: "+root.val+" sum: "+sum+" result: "+result);
	    cur.add(root.val);
	    
	    if(root.left == null && root.right == null && root.val == sum) {
	    	result.add(new ArrayList<Integer> (cur));
	    }
	    else {
	    	if(root.left != null)
	    		System.out.println("root.left: "+root.left.val+" next sum: "+(sum - root.val));
	        pathSum(root.left, sum - root.val, cur, result);
	        
	        if(root.right != null)
	        	System.out.println("root.right: "+root.right.val+" next sum: "+(sum - root.val));
	        pathSum(root.right, sum - root.val, cur, result);
	    }
	    System.out.println("cur: "+cur);
	    cur.remove(cur.size() - 1);
	    System.out.println("cur: "+cur);
	}
	
//	if(root.right != null && root.left != null)
//    	System.out.println("cur: "+cur+" root.left: "+root.left.val+" root.right: "+root.right.val+" root: "+root.val+" sum: "+sum);
//    else
//    	System.out.println("cur: "+cur+" root: "+root.val+" sum: "+sum);
	
	public static void main(String[] args) {

		/*
		 	  5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
	*/
		
		PathSum2 tree = new PathSum2();
		
		tree.root = new TreeNode(5);
		tree.root.left =  new TreeNode(4);
		tree.root.left.left = new TreeNode(11);
		tree.root.left.left.left = new TreeNode(7);
		tree.root.left.left.right = new TreeNode(2);
		tree.root.right = new TreeNode(8);
		tree.root.right.left = new TreeNode(13);
		tree.root.right.right = new TreeNode(4);
		tree.root.right.right.left = new TreeNode(5);
		tree.root.right.right.right = new TreeNode(1);
		
		int sum = 22;
		
		System.out.println(pathSum(tree.root, sum));
	}

}
