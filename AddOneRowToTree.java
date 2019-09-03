package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 623. Add One Row to Tree
 * https://leetcode.com/problems/add-one-row-to-tree/description/
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.
 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root
 * and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.
 * Example 1: Input: A binary tree as following:
	       4
	     /   \
	    2     6
	   / \   / 
	  3   1 5   
	
	v = 1
	
	d = 2
	
	Output: 
	       4
	      / \
	     1   1
	    /     \
	   2       6
	  / \     / 
	 3   1   5   

	Example 2:Input: 
	A binary tree as following:
    
	      4
	     /   
	    2    
	   / \   
	  3   1    
	
	v = 1	
	d = 3

	Output: 
	      4
	     /   
	    2
	   / \    
	  1   1
	 /     \  
	3       1
	Note:
	The given d is in range [1, maximum depth of the given tree + 1].
	The given binary tree has at least one tree node.
	Explanation and Code from: Approach #3 Using Queue (BFS) https://leetcode.com/problems/add-one-row-to-tree/solution/
	Gilt Groupe
	Medium
 */

public class AddOneRowToTree {
	
	TreeNode root;

	public static TreeNode addOneRow(TreeNode root, int v, int d) {
        System.out.println("root: "+root.val+" v: "+v+" d: "+d);
		
		if(d == 1) {
            TreeNode n = new TreeNode(v);
            n.left = root;
            return n;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 1;
        
        while(depth < d-1) {
        	System.out.println("depth: "+depth+" d: "+d);
            Queue<TreeNode> temp = new LinkedList<>();
            
            while(!queue.isEmpty()) {
                TreeNode node = queue.remove();
                System.out.println("node: "+node.val);
                
                if(node.left != null) 
                	temp.add(node.left);
                
                if(node.right != null) 
                	temp.add(node.right);
                
                System.out.println("temp: "+temp);
            }
            queue = temp;
            System.out.println("queue: "+queue+" temp: "+temp+" depth: "+depth);
            
            depth++;
        }
        
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();
            TreeNode temp = node.left;
            
            node.left = new TreeNode(v);
            node.left.left = temp;
            
            temp = node.right;
            
            node.right = new TreeNode(v);
            node.right.right = temp;
        }
        return root;
    }
	
	public static void main(String[] args) {
		AddOneRowToTree tree = new AddOneRowToTree();
		
		tree.root = new TreeNode(4);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(3);
		tree.root.left.right = new TreeNode(5);
		tree.root.right = new TreeNode(6);
		tree.root.right.right = new TreeNode(7);
		
		/*
		 				1
		 			  /   \
		 			 2     3
		 			/
		 		   4
		 */
//		tree.root = new TreeNode(1);
//		tree.root.left = new TreeNode(2);
//		tree.root.left.left = new TreeNode(4);
//		tree.root.right = new TreeNode(3);
		
		int v = 1;
		int d = 3;
		
		tree.root = addOneRow(tree.root, v, d);
		
		System.out.println("        "+tree.root.val);
		System.out.println("      /  \\");
		System.out.println("    "+tree.root.left.val+"      "+tree.root.right.val);
		System.out.println("   / \\   /  \\");
		System.out.println("  "+tree.root.left.left.val+"   "+tree.root.left.right.val+"  "+tree.root.right.left.val+"   "+tree.root.right.right.val);
		System.out.println(" /     \\      \\");
		System.out.println(tree.root.left.left.left.val+"       "+tree.root.left.right.right.val+"     "+tree.root.right.right.right.val);
		System.out.println();
	}

}
