package Tree;

public class BinaryTreeNode {
	
	public int data;
	public BinaryTreeNode left, right, parent, next;
	public int size;
	
	public BinaryTreeNode(int val) {
		data = val;
		left = right = parent = next = null;
		size = 0;
	}
	
	public BinaryTreeNode() {
		
	}
}
