package Tree;

/*
 * 222. Count Complete Tree Nodes
 * https://leetcode.com/problems/count-complete-tree-nodes/description/
 * Given a complete binary tree, count the number of nodes.

	Note:
	
	Definition of a complete binary tree from Wikipedia:
	In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
	
	Example:
	
	Input: 
	    1
	   / \
	  2   3
	 / \  /
	4  5 6
	
	Output: 6
	Medium
	Explanation and Code from: @StefanPochmann https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
 */

public class CountCompleteTreeNodes {

	TreeNode root;
	
    public static int height(TreeNode root) {
    	int height = 0;
    	
    	if(root == null) {
    		return -1;
    	}
    	else {
    		System.out.println("root: "+root.val+" height: "+height);
    		height = 1 + height(root.left);
    	}
    	System.out.println("height: "+height);
        return height;
    }
    
    /*
			h=2	    1
				  /   \
		h=1 	 2     3
				/ \   / 
		h=0    4   5  6 
		
		
		The height of a tree can be found by just going left. Let a single node tree have height 0. Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.
		
		Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height.
		
		If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of nodes in the right subtree.
		If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node plus recursively the number of nodes in the left subtree.
		Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).

     how many nodes does a full binary tree of height h contains?
		2^h - 1
		or you can write :
		(1<<h) - 1

	It's just a another way to do 2^h. If you shift 1 by h places, it is equivalent to doing 2^h (and more optimized so it runs faster).
	For example:
	1 << 0 = 1 or 2^0
	1 << 1 = 2 (10 in binary) or 2^1
	1 << 3 = 8 (1000 in binary) or 2^3
    
     */
    
    public static int countNodes(TreeNode root) {
        int nodes = 0;
        
        System.out.println("root: "+root.val);
        
        int h = height(root);
        System.out.println("h: "+h);
        
        while(root != null) {
        	System.out.println("root: "+root.val+" h: "+h+" nodes: "+nodes);
            
        	if(height(root.right) == h - 1) {	//last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1
        		System.out.println("root: "+root.val+" h: "+h+" nodes: "+nodes);
                nodes = nodes + (2^(h - 1)); //2^h - 1 //1 << h
                root = root.right;
            } 
        	else {
        		System.out.println("root: "+root.val+" h: "+h+" nodes: "+nodes);
        		nodes = nodes + (2^(h-1))-1;
                root = root.left;
            }
            h--;
        }
        System.out.println("h: "+h+" nodes: "+nodes);
        return nodes;
    }
    
    //Refer this
    public static int countNodes1(TreeNode root) {
        if(root == null) 
        	return 0;
        
        System.out.println("root: "+root.val);
        
        int leftHeight = getHeightLeft(root);
        int rightHeight = getHeightRight(root);
        
        System.out.println("leftHeight: "+leftHeight+" rightHeight: "+rightHeight);
        
        //If left and right are equal it means that the tree is complete and hence go for 2^h -1.
        if(leftHeight == rightHeight) {
        	System.out.println("leftHeight: "+leftHeight+" 2^(leftHeight): "+(2^(leftHeight))+" ");
        	return ((2^(leftHeight)) -1);
        }
        //else recursively calculate the number of nodes in left and right and add 1 for root.
        else { 
        	System.out.println("leftHeight: "+leftHeight+" rightHeight: "+rightHeight+" root: "+root.val);
        	
        	int countLeft = countNodes1(root.left);
        	System.out.println("countLeft: "+countLeft);
        	
        	System.out.println("leftHeight: "+leftHeight+" rightHeight: "+rightHeight+" root: "+root.val);
        	
        	int countRight = countNodes1(root.right);
        	
        	System.out.println("countLeft: "+countLeft+" countRight: "+countRight);
        			
        	return countLeft + countRight + 1;
        }
    }
    
    
    public static int getHeightLeft(TreeNode root) {
        int count = 0;
        System.out.println("root: "+root.val);
        
        while(root.left != null) {
            count++;
            root = root.left;
        }
        
        System.out.println("count: "+count);
        return count;
    }
    
    
    public static int getHeightRight(TreeNode root) {
        int count = 0;
        System.out.println("root: "+root.val);
        
        while(root.right!=null) {
            count++;
            root = root.right;
        }
        
        System.out.println("count: "+count);
        return count;
    }

	
	public static void main(String[] args) {
		/*
			 	    1
				  /   \
				 2     3
			    / \   / 
			   4   5  6 
	  
	   */
		System.out.println("2^0: "+(Math.pow(2, 1)));
		
		CountCompleteTreeNodes tree = new CountCompleteTreeNodes();
		
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);
		tree.root.right.left = new TreeNode(6);
		
		System.out.println(countNodes1(tree.root));
	}

}
