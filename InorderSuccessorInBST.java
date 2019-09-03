package Tree;

/*
 * 285. Inorder Successor in BST
 * https://leetcode.com/problems/inorder-successor-in-bst/description/
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST
 * Note: If the given node has no in-order successor in the tree, return null
 * Explanation and Code from: https://leetcode.com/problems/inorder-successor-in-bst/discuss/72653/Share-my-Java-recursive-solution
 * let’s take the successor for example, basically we always want to find p's closest node (in inorder traversal) and the node’s value is larger 
 * than p's value, right? That node can either be p's parent or the smallest node in p’ right branch
 * When the code runs into the else block, that means the current root is either p's parent or a node in p's right branch
 * If it’s p's parent node, there are two scenarios: 1. p doesn’t have right child, in this case, the recursion will eventually return null, 
 * so p's parent is the successor; 2. p has right child, then the recursion will return the smallest node in the right sub tree, and that will be the answer
 * If it’s p's right child, there are two scenarios: 1. the right child has left sub tree, eventually the smallest node from the left sub tree will be the 
 * answer; 2. the right child has no left sub tree, the recursion will return null, then the right child (root) is our answer
 */

public class InorderSuccessorInBST {

	TreeNode root;
	
	/*
			   20						
			 /   \
			8     22
		   / \    / \   
		  4  12  21  25
		 	/  \
		   10  14

 let’s take the successor for example, basically we always want to find p's closest node (in inorder traversal) and the node’s value is larger 
 than p's value, right? That node can either be p's parent or the smallest node in p’ right branch
 When the code runs into the else block, that means the current root is either p's parent or a node in p's right branch
 If it’s p's parent node, there are two scenarios: 1. p doesn’t have right child, in this case, the recursion will eventually return null, 
 so p's parent is the successor; 2. p has right child, then the recursion will return the smallest node in the right sub tree, and that will be the
 answer. If it’s p's right child, there are two scenarios: 1. the right child has left sub tree, eventually the smallest node from the left sub tree
 will be the answer; 2. the right child has no left sub tree, the recursion will return null, then the right child (root) is our answer

*/
	public static TreeNode successor(TreeNode root, TreeNode p) {
		  if(root == null) {
		    return null;
		  }
		  System.out.println("root: "+root.val+" p: "+p.val);
		  
		  if(root.val <= p.val) {
			  TreeNode right = successor(root.right, p);
			  System.out.println("right: "+right.val);
			  return right;
		  } 
		  else {
		    TreeNode left = successor(root.left, p);
		    
		    if (left != null) { 
		    	System.out.println("left: "+left.val+" root: "+root.val);
		    	return left;
		    }
		    else 
		    	return root;
		  }
	}
	
	//Refer this: https://www.youtube.com/watch?v=kdK_5rl1cVw
	//@sohammehta https://leetcode.com/problems/inorder-successor-in-bst/discuss/72653/Share-my-Java-recursive-solution
	//https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    	System.out.println("root: "+root.val+" p: "+p.val);
    	
        TreeNode succ = null;
        
        while(root != null) {
        	System.out.println("root: "+root.val+" p: "+p.val+" succ: "+succ);
            
        	if(root.val > p.val) {
                succ = root;
                root = root.left;
            }
            else if(root.val <= p.val) {
                root = root.right;
            }
        }

        return succ;
    }
    
    public static TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
    	System.out.println("root: "+root.val+" p: "+p.val);
    	
        TreeNode pred = null;
        
        while(root != null) {
        	System.out.println("root: "+root.val+" p: "+p.val+" pred: "+pred);
            
        	if(root.val >= p.val) {
                root = root.left;
            }
            else if(root.val < p.val) {
            	pred = root;
                root = root.right;
            }
        }

        return pred;
    }

	
	public static void main(String[] args) {
		InorderSuccessorInBST tree = new InorderSuccessorInBST();
		/*
				   20						
				 /   \
				8     22
			   / \    / \   
			  4  12  21  25
			    /  \
			   10  14
		*/

		tree.root = new TreeNode(20);
		tree.root.left = new TreeNode(8);
		tree.root.left.left = new TreeNode(4);
		tree.root.right = new TreeNode(22);
		tree.root.left.right = new TreeNode(12);
		tree.root.left.right.right = new TreeNode(14);
		tree.root.left.right.left = new TreeNode(10);
		tree.root.right.left = new TreeNode(21);
		tree.root.right.right = new TreeNode(25);
		
		//System.out.println(successor(tree.root, tree.root.left).val);		//8
		System.out.println(inorderSuccessor(tree.root, tree.root.left).val);		//8
	}

}
