package Tree;

import java.util.Arrays;

/*
 * 776. Split BST
 * https://leetcode.com/problems/split-bst/
 * Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller
 * or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree 
 * contains a node with value V. Additionally, most of the structure of the original tree should remain. Formally, for any child C with parent P in the 
 * original tree, if they are both in the same subtree after the split, then node C should still have the parent P.
 * You should output the root TreeNode of both subtrees after splitting, in any order.
 * Example 1: Input: root = [4,2,6,1,3,5,7], V = 2; Output: [[2,1],[4,3,6,null,null,5,7]]
 * Explanation: Note that root, output[0], and output[1] are TreeNode objects, not arrays.
 * The given tree [4,2,6,1,3,5,7] is represented by the following diagram:
          4
        /   \
      2      6
     / \    / \
    1   3  5   7

	while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1

 * Explanation and Code from: @ywng https://leetcode.com/problems/split-bst/discuss/114861/Java-Recursion-in-O(logn)
 * https://www.youtube.com/watch?v=ADun2n_ueZQ
 */

public class SplitBST {

	TreeNode root;
	
	/*
	 	Just think about the current root, after processing, it will need to return [smaller/eq, larger] subtrees

		if root.val <=V, all nodes under root.left(including root) will be in the smaller/eq tree,
		we then split the root.right subtree into smaller/eq, larger, the root will need to concat the smaller/eq from the subproblem result (recursion).
		
		Similarly for the case root.val>V
		
		The runtime will be O(logn) if the input is balanced BST. Worst case is O(n) if it is not balanced.
	 */
    public static TreeNode[] splitBST(TreeNode root, int V) {
        // res[0]: root with values > V
        // res[1]: root with values <= V

        if (root == null)
        {
            return new TreeNode[]{null, null};
        }
        System.out.println("root: "+root.val+" V: "+V);

        if (root.val <= V)
        {
            // The cut off is somewhere in the right subtree relative to root
            TreeNode[] res = splitBST(root.right, V);

            // res[1] is the node for all values <= V found in the right sub tree
            // since it's form the right sub tree, all values must be greater than root
            // so safe to do the following
            root.right = res[1];

            // root along with everything in its left subtree are already <= V
            // so after setting root.right=res[1], now root represents all nodes <= V

            // the node for all values greater than V is still res[0], some node found on the right subtree
            
            return new TreeNode[]{res[0], root};
        }
        else
        {
            // The cut off is somewhere in the left subtree relative to root
            TreeNode[] res = splitBST(root.left, V);

            // res[0] is the node for all values >V found in the left sub tree
            // since it's from the left sub tree, all values must be less than root
            // so safe to do the following
            
            root.left = res[0];
            
            // root along with everything in its right subtree are already >V
            // so after setting root.left=res[0], now root represents all values >V
            
            // the node for all values less than V is still res[1], some node found on the left subtree 
            
            return new TreeNode[]{root, res[1]};
        }
    }
    
    //Refer this
    public static TreeNode[] splitBST1(TreeNode root, int V) {
        if(root == null) { 
        	return new TreeNode[]{null, null};
        }
        System.out.println("root: "+root.val+" V: "+V);
        
        TreeNode[] splitted;	// splitted[0]: root with values <= V; splitted[1]: root with values > V
        
        if(root.val <= V) {
            splitted = splitBST1(root.right, V);
            root.right = splitted[0];
            splitted[0] = root;
        } 
        else {
            splitted = splitBST1(root.left, V);
            root.left = splitted[1];
            splitted[1] = root;
        }
        
        return splitted;
    }
	
	public static void main(String[] args) {
		SplitBST tree = new SplitBST();
		/*
	 			          4
				        /   \
				      2      6
				     / \    / \
				    1   3  5   7
		*/

		tree.root = new TreeNode(4);
		tree.root.left = new TreeNode(2);
		tree.root.left.left = new TreeNode(1);
		tree.root.left.right = new TreeNode(3);
		tree.root.right = new TreeNode(6);
		tree.root.right.left = new TreeNode(5);
		tree.root.right.right = new TreeNode(7);

		TreeNode[] result = splitBST1(tree.root, 2);
		System.out.println(Arrays.toString(result));
	}
}