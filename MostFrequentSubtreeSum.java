package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * 508. Most Frequent Subtree Sum
 * https://leetcode.com/problems/most-frequent-subtree-sum/description/
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 * Examples 1: Input:
 * 					  5
 * 					 /  \
 * 					2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * Examples 2: Input:
 *					
 *					  5
 *					 /  \
 *					2   -5
 * return [2], since 2 happens twice, however -5 only occur once.					
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer
 * Explanation and Code from: https://leetcode.com/problems/most-frequent-subtree-sum/discuss/98664/Verbose-Java-solution-postOrder-traverse-HashMap-(18ms)
 * Amazon
 * Medium					
 */

public class MostFrequentSubtreeSum {

	public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();	//key-sum; value-frequency of sum; e.g. how many times sum=2 has occured
    public static List<Integer> res = new ArrayList<Integer>();
    public static int freq = 0;
    
    TreeNode root;
    
    /*
     			   5
				 /  \
				2   -3
     */
    
    public static int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) { 
        	return new int[0];
        }
        
        System.out.println("root: "+root.val);
        helper(root);
        
        int[] ret = new int[res.size()];
        for(int i=0; i<res.size(); i++) {
            ret[i] = res.get(i);
        }
        System.out.println("ret: "+Arrays.toString(ret));
        return ret;
    }
    
    public static void helper(TreeNode root) {
        if(root == null) 
        	return;
        
        int sum = computeSum(root);
        System.out.println("sum: "+sum+" root: "+root.val);
        
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        System.out.println("map: "+map+" map.get(sum): "+map.get(sum)+" freq: "+freq);
        
        if(map.get(sum) == freq) {
            res.add(sum);
        }
        else if(map.get(sum) > freq) {
            res.clear();
            res.add(sum);
        }
        
        freq = Math.max(freq, map.get(sum));
        System.out.println("freq: "+freq+" res: "+res);
        
        helper(root.left);
        helper(root.right);
    }
    
    public static int computeSum(TreeNode root) {
        int s = root.val;
        System.out.println("root s: "+s);
        
        if(root.left != null) 
        	s = s + computeSum(root.left);
        System.out.println("left s: "+s);
        
        if(root.right != null) 
        	s = s + computeSum(root.right);
        System.out.println("right s: "+s);
        
        return s;
    }
	
	public static void main(String[] args) {
		MostFrequentSubtreeSum tree = new MostFrequentSubtreeSum();
		
		tree.root = new TreeNode(5);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(-3);
		
		System.out.println(findFrequentTreeSum(tree.root));
	}

}
