package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 652. Find Duplicate Subtrees
 * https://leetcode.com/problems/find-duplicate-subtrees/description/
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 * Example 1: 
 *        1
 *       / \
 *      2   3
 *     /   / \
 *    4   2   4
 *       /
 *      4
 * The following are two duplicate subtrees:
 *      2
 *     /
 *    4
 * and
 *    4
 * Therefore, you need to return above trees' root in the form of a list.
 * Google
 * Medium
 */

//Refer this
public class FindDuplicateSubtrees_Me {

    public static Map<String, TreeNode> trees;
    public static List<TreeNode> ans;

    TreeNode root;
    
    /*
     		        1
			       / \
			      2   3
			     /   / \
			    4   2   4
			       /
			      4
     */
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        trees = new HashMap<String, TreeNode>();
        ans = new ArrayList<TreeNode>();
        lookup(root);
        return ans;
    }

    public static int lookup(TreeNode node) {
        if(node == null) { 
        	return 0;
        }
        
        System.out.println("node: "+node.val);
        
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        System.out.println("serial: "+serial+" node: "+node.val);
        
        if(trees.containsKey(serial)) {
        	 ans.add(node);
        	 trees.remove(serial);
        	 System.out.println("ans node: "+node.val);
        }
        else { 
        	trees.put(serial, node);
        }
        return node.val;
    }
	
	public static void main(String[] args) {
		FindDuplicateSubtrees_Me tree = new FindDuplicateSubtrees_Me();
		
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
