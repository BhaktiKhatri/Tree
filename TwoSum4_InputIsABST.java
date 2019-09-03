package Tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 653. Two Sum IV - Input is a BST
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 * Explanation and Code from: Approach #3 Using BST https://leetcode.com/problems/two-sum-iv-input-is-a-bst/solution/
 *   	
 */

public class TwoSum4_InputIsABST {
	
	TreeNode root;
	
	/*
			 			    5
						   / \
						  3   6
						 / \   \
						2   4   7

						Target = 9
	 */
    public static boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<Integer>();
        
        inorder(root, list);
        System.out.println("root: "+root.val+" list: "+list+" k: "+k);
        
        int l = 0;
        int r = list.size() - 1;
        System.out.println("l: "+l+" r: "+r);
        
        while(l < r) {
        	System.out.println("l: "+l+" list.get(l): "+list.get(l)+" r: "+r+" list.get(r): "+list.get(r));
        	
            int sum = list.get(l) + list.get(r);
            System.out.println("sum: "+sum+" k: "+k);
            
            if(sum == k) {
                return true;
            }
            
            if(sum < k) {
                l++;
            }
            else {
                r--;
            }
        }
        return false;
    }
    
    public static void inorder(TreeNode root, List < Integer > list) {
        if(root == null) {
            return;
        }
        
        System.out.println("root: "+root.val+" list: "+list);
        
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
	
	public static void main(String[] args) {
			/*
			 				    5
							   / \
							  3   6
							 / \   \
							2   4   7

			 */

			TwoSum4_InputIsABST tree = new TwoSum4_InputIsABST();
	
			tree.root = new TreeNode(5);
			tree.root.left = new TreeNode(3);
			tree.root.left.left = new TreeNode(2);
			tree.root.left.right = new TreeNode(4);
			tree.root.right = new TreeNode(6);
			tree.root.right.right = new TreeNode(7);
	
			int k = 9;
			
			System.out.println(findTarget(tree.root, k));
	}
}