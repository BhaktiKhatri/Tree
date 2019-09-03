package Tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * 449. Serialize and Deserialize BST same as: 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-bst/description/
 * 297. Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
	Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
	Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
	For example, you may serialize the following tree
	    1
	   / \
	  2   3
	     / \
	    4   5
	as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
	Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
	Code and explanation from: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74253/Easy-to-understand-Java-Solution
 */

public class SerializeDeserializeBinaryTree {
	
		 /*
				1
			   / \
			  2   3
			     / \
			    4   5
		 */
		
	TreeNode root;
	private static final String spliter = ",";
    private static final String NN = "X";

    //The idea is simple: print the tree in pre-order traversal and use “X” to denote null node and split node with “,”. 
    //We can use a StringBuilder for building the string on the fly. For deserializing, we use a Queue to store the pre-order traversal
    //and since we have “X” as null node, we know exactly how to where to end building subtrees.
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        System.out.println("root: "+root.val+" sb: "+sb);
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if(node == null) {
        	System.out.println("sb: "+sb);
            sb.append(NN).append(spliter);
        } 
        else {
        	System.out.println("node: "+node.val+" sb: "+sb);
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        System.out.println("data: "+data);
        nodes.addAll(Arrays.asList(data.split(spliter)));
        System.out.println("nodes: "+nodes);
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        System.out.println("val: "+val);
        
        if(val.equals(NN)) {
        	return null;
        }
        else {
        	System.out.println("val: "+val);
        	TreeNode node = new TreeNode(Integer.valueOf(val));
        	node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
	
	public static void main(String[] args) {
		SerializeDeserializeBinaryTree tree = new SerializeDeserializeBinaryTree();
		
		/*
		 		1
		 	   / \
		 	  2   3
		 	     / \
		 	    4   5
		 */
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.right.left = new TreeNode(4);
		tree.root.right.right = new TreeNode(5);
		
		System.out.println(tree.serialize(tree.root));
		System.out.println(tree.deserialize("1,2,X,X,3,4,X,X,5,X,X,").val);
	}

}
