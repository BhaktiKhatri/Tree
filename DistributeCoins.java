package Tree;

/*
 * 979. Distribute Coins in Binary Tree
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  
 * (The move may be from parent to child, or from child to parent.)
 * Return the number of moves required to make every node have exactly one coin.
 * Code from:@lee215 https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221930/JavaC%2B%2BPython-Recursive-Solution
 * Explanation from: https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C%2B%2B-with-picture-post-order-traversal
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/solution/
 * Time Complexity: O(N), where N is the number of nodes in the tree.
 * Space Complexity: O(H), where H is the height of the tree. 
 */

public class DistributeCoins {

    static int res = 0;
    TreeNode root;
    
    /*
     * Write a dfs helper, return the number of coins given to the parent.
     * We traverse children first (post-order traversal), and return the balance of coins. 
     * For example, if we get '+3' from the left child, that means that the left subtree has 3 extra coins to move out. 
     * If we get '-1' from the right child, we need to move 1 coin in. So, we increase the number of moves by 4 (3 moves out left + 1 moves in right). 
     * We then return the final balance: -3 (left) + 1 (right) - 1 (keep one coin for the root) + r->val (coins in the root).
     */
    public static int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    public static int dfs(TreeNode root) {
        if(root == null) { 
        	return 0;
        }
        
        System.out.println("root: "+root.val);
        
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        System.out.println("root: "+root.val+" left: "+left+" right: "+right+" res: "+res);
        
        res = res + Math.abs(left) + Math.abs(right);
        System.out.println("res: "+res+" root: "+root.val+" left: "+left+" right: "+right);
        
        return root.val + left + right - 1;
    }
	
	public static void main(String[] args) {
		DistributeCoins tree = new DistributeCoins();
		/*
	 			    4
			       / \
			      2   7
			     / \
			    1   3
		*/

		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(0);
		//tree.root.left.left = new TreeNode(1);
		tree.root.right = new TreeNode(0);
		//tree.root.right.right = new TreeNode(9);
		tree.root.left.right = new TreeNode(3);
		
		System.out.println(distributeCoins(tree.root));
	}
}