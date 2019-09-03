package Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * 663. Equal Tree Partition
 * https://leetcode.com/problems/equal-tree-partition/description/
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.
 * Example 1: Input:     
    5
   / \
  10 10
    /  \
   2   3

	Output: True
	Explanation: 
	    5
	   / 
	  10
	      
	Sum: 15
	
	   10
	  /  \
	 2    3
	
	Sum: 15
	Example 2:
	Input:     
	    1
	   / \
	  2  10
	    /  \
	   2   20
	
	Output: False
	Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
	Note:
	The range of tree node value is in the range of [-100000, 100000].
	1 <= n <= 10000
	Explanation and Code from: @zc94zc https://leetcode.com/problems/equal-tree-partition/discuss/106727/JavaC++-Simple-solution-with-only-one-HashMaplessgreater.
	Amazon
	Medium
 */

public class EqualTreePartition {

	TreeNode root;
	
	/*
	 	https://translate.google.com/translate?hl=en&sl=zh-CN&u=http://www.cnblogs.com/grandyang/p/7550360.html&prev=search
	 	
	 	This question allows us to divide the equivalence tree. This means that when one edge is removed, the sum of the nodes of the two trees that are divided needs
	 	to be equal. By observing the examples in the topic, we can see that if we change the node value of each node to the sum of the node values ​​of all its child 
	 	nodes plus the current node value, then for Example 1 The node value of the root node becomes 30, and the node of the disconnection position becomes 15, 
	 	then we can find out that if the node value of the disconnection position is half of the value of the root node, it exists. Equivalent division.
	 
	 				    5
					   / \
					  10 10
					    /  \
					   2   3
	 */
	public static boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();	//key-sum; value-freq of sum
        
        int sum = getsum(root, map);
        System.out.println("sum: "+sum+" map: "+map);
        
        if(sum == 0)
        	return map.getOrDefault(sum, 0) > 1;
        
        return sum % 2 == 0 && map.containsKey(sum/2);
    }
    
    public static int getsum(TreeNode root, Map<Integer, Integer> map) {
        if(root == null)
        	return 0;
        
        System.out.println("root: "+root.val+" map: "+map);
        
        int nodeSum = root.val;
        System.out.println("nodeSum: "+nodeSum);
        
        int leftSum = getsum(root.left, map);
        System.out.println("leftSum: "+leftSum);
        
        int rightSum = getsum(root.right, map);
        System.out.println("rightSum: "+rightSum);
        
        int cur = nodeSum + leftSum + rightSum;
        System.out.println("cur: "+cur+" map: "+map);
        
        map.put(cur, map.getOrDefault(cur,0) + 1);
        
        return cur;
    }
    
    public static Set<Integer> set = new HashSet<>();
    
    public static boolean checkEqualTree1(TreeNode root) {
        if (root == null) 
            return false;
        
        System.out.println("root: "+root.val);
        int sum = dfs(root);
        
        //int sum = root.val + dfs(root.left) + dfs(root.right);
        System.out.println("sum: "+sum);
        
        return (sum % 2 == 0) && set.contains(sum / 2);
    }
    
    public static int dfs(TreeNode node) {
        if (node == null) 
        	return 0;
        
        System.out.println("node: "+node.val);
        
        int leftSum = dfs(node.left);
        System.out.println("node: "+node.val+" leftSum: "+leftSum);
        
        int rightSum = dfs(node.right);
        System.out.println("node: "+node.val+" rightSum: "+rightSum);
        
        int sum = node.val + leftSum + rightSum;
        System.out.println("sum: "+sum);
        
        set.add(sum);
        System.out.println("set: "+set);
        
        return sum;
    }
	
	public static void main(String[] args) {
		/*
		 	    5
			   / \
			  10 10
			    /  \
			   2   3
		 */
		
		/*
		 	  0
			 / \
			-1  1

		 */
		
		EqualTreePartition tree = new EqualTreePartition();

//		tree.root = new TreeNode(0);
//		tree.root.left = new TreeNode(-1);
//		tree.root.right = new TreeNode(1);
		
		tree.root = new TreeNode(5);
		tree.root.left = new TreeNode(10);
		tree.root.right = new TreeNode(10);
		tree.root.right.left = new TreeNode(2);
		tree.root.right.right = new TreeNode(3);
		
		System.out.println(checkEqualTree(tree.root));
	}

}
