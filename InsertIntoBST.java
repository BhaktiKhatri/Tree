package Tree;

/*
 * 701. Insert into a Binary Search Tree
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 * For example, Given the tree:
        4
       / \
      2   7
     / \
    1   3
  And the value to insert: 5
  You can return this binary search tree:
         4
       /   \
      2     7
     / \   /
    1   3 5
	
	Code from: @calis https://leetcode.com/problems/insert-into-a-binary-search-tree/discuss/150757/java-iterative-100
	https://www.youtube.com/watch?v=bmaeYtlO2OE
	Time Complexity: O(N)
 */

public class InsertIntoBST {

	TreeNode root;
	
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) { 
        	return new TreeNode(val);
        }
        
        System.out.println("root: "+root.val+" val: "+val);
        
        TreeNode cur = root;
        while(true) {
        	System.out.println("cur.val: "+cur.val);
            
        	if(cur.val <= val) {
                if(cur.right != null) { 
                	cur = cur.right;
                }
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } 
        	else {
                if(cur.left != null) { 
                	cur = cur.left;
                }
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
    
    //Refer this: https://www.youtube.com/watch?v=bmaeYtlO2OE
    public static TreeNode insertIntoBST1(TreeNode root, int val) {
    	if(root == null) {
    		return new TreeNode(val);
    	}
    	
    	System.out.println("val: "+val);
    	
    	TreeNode cur = root;
    	TreeNode parent = null;
    
    	while(cur != null) {
    		parent = cur;
    		
    		System.out.println("cur: "+cur.val+" parent: "+parent.val);
    		
    		if(cur.val <= val) {
    			cur = cur.right;
    		}
    		else {
    			cur = cur.left;
    		}
    	}
    	
    	if(parent.val <= val) {
			parent.right = new TreeNode(val);
		}
		else {
			parent.left = new TreeNode(val);
		}
    	
    	return root;
    }
    
	public static void main(String[] args) {
		InsertIntoBST tree = new InsertIntoBST();
		/*
	 			    4
			       / \
			      2   7
			     / \
			    1   3
		*/

//		tree.root = new TreeNode(4);
//		tree.root.left = new TreeNode(2);
//		tree.root.left.left = new TreeNode(1);
//		tree.root.right = new TreeNode(7);
//		//tree.root.right.right = new TreeNode(9);
//		tree.root.left.right = new TreeNode(3);

		tree.root = new TreeNode(10);
		tree.root.left = new TreeNode(8);
		tree.root.left.left = new TreeNode(7);
		tree.root.left.left.left = new TreeNode(5);
		tree.root.left.right = new TreeNode(9);
		tree.root.right = new TreeNode(12);
		

		
		System.out.println(insertIntoBST1(tree.root, 6).val);
	}
}