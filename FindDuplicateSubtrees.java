package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * 652. Find Duplicate Subtrees
 * https://leetcode.com/problems/find-duplicate-subtrees/description/
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 * Example 1: 
 *	        1
 *	       / \
 *	      2   3
 *	     /   / \
 *	    4   2   4
 *	       /
 *	      4
 * The following are two duplicate subtrees:
 *	      2
 *	     /
 *	    4
 *	and
 *	    4
 * Therefore, you need to return above trees' root in the form of a list: [2, 4]
 * Explanation and Code from: @compton_scatter https://leetcode.com/problems/find-duplicate-subtrees/discuss/106011/Java-Concise-Postorder-Traversal-Solution
 * Medium
 * Google
 */

public class FindDuplicateSubtrees {
	
	TreeNode root;

	public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
	    List<TreeNode> res = new LinkedList<>();
	    postorder(root, new HashMap<String, Integer>(), res);	//map key-serial; value-count of serial; e.g. 4,#,# appears 3 times
	    return res;
	}

	public static String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
	    if (cur == null) 
	    	return "#";  
	    
	    int data = cur.val; 
	    System.out.println("data: "+data);
	    
	    String leftSerial = postorder(cur.left, map, res);
	    String rightSerial = postorder(cur.right, map, res);
	    
	    System.out.println("cur: "+cur.val+" leftSerial: "+leftSerial+" rightSerial: "+rightSerial);
	    
	    String serial = data + "," + leftSerial + "," + rightSerial;
	    System.out.println("serial: "+serial+" map: "+map+" res: "+res);
	    
	    if(map.getOrDefault(serial, 0) == 1) 
	    	res.add(cur);
	    
	    map.put(serial, map.getOrDefault(serial, 0) + 1);
	    
	    System.out.println("map: "+map+" serial: "+serial);
	    
	    return serial;
	}
	
	public static void main(String[] args) {
		FindDuplicateSubtrees tree = new FindDuplicateSubtrees();
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(4);
		tree.root.right = new TreeNode(3);
		tree.root.right.right = new TreeNode(4);
		tree.root.right.left = new TreeNode(2);
		tree.root.right.left.left = new TreeNode(4);
	
		System.out.println(findDuplicateSubtrees(tree.root));
	}
}