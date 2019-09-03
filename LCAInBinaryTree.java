package Tree;

/*
 * 236. Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * Explanation and Code from: https://www.youtube.com/watch?v=13m9ZCB8gjw
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LowestCommonAncestorInBinaryTree.java
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C%2B%2BJavaPythonRuby
 * Time Complexity: O(N)
 */

public class LCAInBinaryTree {

	TreeNode root;
	
	/*
	 * evaluate 3 cases: 4,5; 8,4; 0,4
	 */
	
	public static TreeNode lca(TreeNode root, TreeNode n1, TreeNode n2) {
        if(root == null) {
            return null;
        }
        System.out.println("root: "+root.val);
        
        if(root == n1 || root == n2) {	//n1=5 & n2=8; left of 4 = null & right of 4 = 5 as 5=n1; so this line returns 5 for 4
            return root;
        }
        
        System.out.println("root: "+root.val+" n1: "+n1.val+" n2: "+n2.val);
        
        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);

        System.out.println("root: "+root.val+" left: "+left+" right: "+right);
        
      //means root is the ancestor; example: 2 gets 0 from left and 5 from right i.e both not null values so 2 is the ancestor of 0 and 5
        if(left != null && right != null) {		
        	System.out.println("left: "+left.val+" right: "+right.val);
            return root;
        }
        
        return left != null ? left : right;	//left or right whichever is the not null node is the ancestor
    }
	
	public static TreeNode lca1(TreeNode root, TreeNode n1, TreeNode n2) {
        if(root == null) {
            return null;
        }
        
        if(root == n1 || root == n2) {	//n1=5 & n2=8; left of 4 = null & right of 4 = 5 as 5=n1; so this line returns 5 for 4
            return root;
        }
        
        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);

        
        //means root is the ancestor; example: 2 gets 0 from left and 5 from right i.e both not null values so 2 is the ancestor of 0 and 5
        if(left != null && right != null) {		
            return root;
        }
        
        return left != null ? left : right;	//left or right whichever is the not null node is the ancestor
    }
	
	public static void main(String[] args) {
		LCAInBinaryTree tree = new LCAInBinaryTree();
		
		/*
		 		    _______6______
			       /              \
			    ___2__          ___8__
			   /      \        /      \
			   0      _4       7       9
			         /  \
			         3   5
		 */
		
		//tree.root = new TreeNode(2);
		//tree.root.left = new TreeNode(1);
		
		tree.root = new TreeNode(6);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(8);
		tree.root.left.left = new TreeNode(0);
		tree.root.left.right = new TreeNode(4);
		tree.root.left.right.left = new TreeNode(3);
		tree.root.left.right.right = new TreeNode(5);
		tree.root.right.left = new TreeNode(7);
		tree.root.right.right = new TreeNode(9);

		//0,5; 4,8; 8,9
		//2,5; 8,11; 8,7
		//System.out.println(findLCA(tree.root, tree.root.left.left, tree.root.left.right.left).data);
		
		System.out.println(lca(tree.root, tree.root.left.right, tree.root.left.right.right).val);
	}
}