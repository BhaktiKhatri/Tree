package Tree;

import java.util.Stack;

/*
 * 230. Kth Smallest Element in a BST
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up: What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * Explanation and Code from: 3rd Code: DFS in-order iterative https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python):-Binary-Search-in-order-iterative-and-recursive
 * https://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/
 * The best performance is we just have to count the nodes for once (i.e. kth is root), which is O(n); the worse/average case when we need count nodes
 * for each subtree traversal, binary search is always log(n), and number of traversed subtrees could be n, then as total is O(nlog(n))
 * Google, Bloomberg, Uber
 * Medium
 */

public class KthSmallestElementInBST {

	TreeNode root;
	
	/*
	   20						
	  /  \
	 8    22
	/ \
	4   12
	   /  \
	  10   14

	20-8-4-12-10-14-22
*/

	//DFS in-order iterative //final prefer this
	public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        System.out.println("root: "+root.val+" k: "+k);
        
        while(root != null) {
        	System.out.println("root: "+root.val+" stack: "+stack);
            stack.push(root);
            root = root.left;
        }
        System.out.println("stack: "+stack);
            
        while (k != 0) {
            TreeNode n = stack.pop();
            k--;
            
            System.out.println("k: "+k+" n: "+n.val);
            
            if(k == 0) {
            	return n.val;
            }
            TreeNode right = n.right;
            
            while(right != null) {
            	System.out.println("right: "+right.val);
                stack.push(right);
                right = right.left;
            }
        }
        return -1; // never hit if k is valid
	}
	
	public static void main(String[] args) {
		KthSmallestElementInBST tree = new KthSmallestElementInBST();

		/*
						   20						
						  /  \
						 8    22
						/ \
					   4   12
					   	  /  \
					   	 10   14
		*/
		
		tree.root = new TreeNode(20);
		tree.root.left = new TreeNode(8);
		tree.root.left.left = new TreeNode(4);
		tree.root.right = new TreeNode(22);
		tree.root.left.right = new TreeNode(12);
		tree.root.left.right.right = new TreeNode(14);
		tree.root.left.right.left = new TreeNode(10);
		
		int k = 5;
		
		System.out.println(kthSmallest(tree.root, k));
	}

}
