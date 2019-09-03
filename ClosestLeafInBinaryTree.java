package Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 742. Closest Leaf in a Binary Tree
 * https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.
 * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.
 * Example 1: Input: root = [1, 3, 2], k = 1
	Diagram of binary tree:
	          1
	         / \
	        3   2
	
	Output: 2 (or 3)
	Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
	
	Example 2: Input: root = [1], k = 1; Output: 1
	Explanation: The nearest leaf node is the root node itself.
	Example 3: Input: root = [1,2,3,4,null,null,null,5,null,6], k = 2
	Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6
	Output: 3
	Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
	Note:
	root represents a binary tree with at least 1 node and at most 1000 nodes.
	Every node has a unique node.val in range [1, 1000].
	There exists some node in the given binary tree for which node.val == k.
 *  Explanation and Code from: @cwleoo https://leetcode.com/problems/closest-leaf-in-a-binary-tree/discuss/109960/Java-DFS-%2B-BFS-27ms
 */

public class ClosestLeafInBinaryTree {

	TreeNode root;
	
	/*
	 * it is the same idea as BFS in a graph. Use DFS to find target TreeNode first, then use BFS to find next level. Because BFS it one layer by one layer, 
	 * the first Leaf Node will be one of the answers.
	 * 
	 * First, preform DFS on root in order to find the node whose val = k, at the meantime use HashMap to keep record of all back edges from child to parent;
	 * Then perform BFS on this node to find the closest leaf node.
	 */
	public static int findClosestLeaf(TreeNode root, int k) {
		if(root.val == k && root.left == null && root.right == null)
            return root.val;
		
		System.out.println("root: "+root.val+" k: "+k);
		
        Map<TreeNode, TreeNode> map = new HashMap<>();	//key: node with value k; value: parent of node with value k; // store all edges that trace node back to its parent
        Set<TreeNode> visited = new HashSet<>();        // store all visited nodes

        TreeNode match = dfs(root, map, k);
        
        System.out.println("match: "+match.val);
        
        Queue<TreeNode> que = new LinkedList<>();	// the queue used in BFS
        que.add(match);
        visited.add(match);

	    
        while(!que.isEmpty()) {
	      TreeNode cur = que.poll();
	      System.out.println("cur: "+cur.val);
	      
	      if(cur.left == null && cur.right == null) {
	        return cur.val;
	      }
	      
	      if(cur.left != null && visited.add(cur.left)) {  
	    	  que.add(cur.left);
	      }
	      
	      if(cur.right != null && visited.add(cur.right)) { 
	    	  que.add(cur.right);
	      }
	      
	      if(map.containsKey(cur) && visited.add(map.get(cur))) {
	        que.add(map.get(cur));
	        map.remove(cur);
	      }
	    }
	    return -1;
    }
    
    public static TreeNode dfs(TreeNode root, Map<TreeNode, TreeNode> map,int k) {
        if(root == null) 
        	return null;
        
        if(root.val == k) 
        	return root;
        
        System.out.println("root: "+root.val);
        
        TreeNode left = dfs(root.left, map, k);
        
        if(left != null) {
            map.put(root.left, root);
            return left;
        }
        
        TreeNode right = dfs(root.right, map, k);
        
        if(right != null) {
            map.put(root.right, root);
            return right;
        }
        return null;
    }
	
	public static void main(String[] args) {
		ClosestLeafInBinaryTree tree = new ClosestLeafInBinaryTree();
		/*
	 			  1
		         / \
		        3   2
		*/

		//int k = 1;
		
		//tree.root = new TreeNode(1);
		//tree.root.left = new TreeNode(3);
		//tree.root.left.left = new TreeNode(1);
		//tree.root.right = new TreeNode(2);
		//tree.root.right.right = new TreeNode(9);
		//tree.root.left.right = new TreeNode(3);
		
		/*
			       	 1
		            / \
		           2   3
		          /
		         4
		        /
		       5
		      /
		     6

		 */

		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.left.left = new TreeNode(5);
		tree.root.left.left.left.left = new TreeNode(6);
		tree.root.right = new TreeNode(3);

		int k = 2;
		System.out.println(findClosestLeaf(tree.root, k));
	}
}