package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/*
 * 654. Maximum Binary Tree
 * https://leetcode.com/problems/maximum-binary-tree/description/
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * The root is the maximum number in the array
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number
 * Construct the maximum tree by the given array and output the root node of this tree
 * Example 1: Input: [3,2,1,6,0,5]; Output: return the tree root node representing the following tree:
 *      6
 *    /   \
 *   3     5
 *    \    / 
 *     2  0   
 *       \
 *        1
 * Note:The size of the given array will be in the range [1,1000]
 * Explanation and Code from: @hjy06 https://leetcode.com/problems/maximum-binary-tree/discuss/106146/C++-O(N)-solution
 * https://leetcode.com/problems/maximum-binary-tree/discuss/106147/c-9-lines-on-log-n-map-plus-stack-with-binary-search
 * Time Complexity: O(n) because each element will get added and popped from the stack exactly once
 * Microsoft
 * Medium
 */

public class MaximumBinaryTree {

	/*
	 * We populate right subtree if numbers are decreasing. If the current number is larger, the portion of the right subtree that is smaller will become a left
	 * subtree of the current number, thus making the current number the leaf/smallest in the right subtree.
	 * The key idea is:
	 * We scan numbers from left to right, build the tree one node by one step;
	 * We use a stack to keep some (not all) tree nodes and ensure a decreasing order;
	 * For each number, we keep pop the stack until empty or a bigger number; The bigger number (if exist, it will be still in stack) is current number’s root,
	 * and the last popped number (if exist) is current number’s left child (temporarily, this relationship may change in the future); Then we push current number
	 * into the stack.
	 */
	
    //Stack<TreeNode> list = new Stack<TreeNode>();
	//Deque<TreeNode> list = new ArrayDeque<>();
	public static TreeNode constructMaximumBinaryTree(int[] nums) {
        LinkedList<TreeNode> list = new LinkedList<>();
		System.out.println("nums: "+Arrays.toString(nums));
		
        for(int num: nums) {
        	System.out.println("num: "+num);
        	
            TreeNode cur = new TreeNode(num);
            System.out.println("cur: "+cur.val+" list: "+list);
            
            while(!list.isEmpty() && list.peek().val < cur.val) {
            	System.out.println("list.peek().val: "+list.peek().val+" cur.val: "+cur.val);
                cur.left = list.pop();
            }
            System.out.println("list: "+list+" cur: "+cur.val);
            
            if(!list.isEmpty()) {
            	System.out.println("list.peek(): "+list.peek().val);
                list.peek().right = cur;
            }
            list.push(cur);
            System.out.println("list: "+list+" cur: "+cur.val);
        }
        System.out.println("list.peekLast(): "+list.peekLast().val+" list: "+list);
        return list.peekLast();
    }
	
	public static void main(String[] args) {
		int[] nums = {3,2,1,6,0,5};
		
		System.out.println(constructMaximumBinaryTree(nums).val);
	}

}
