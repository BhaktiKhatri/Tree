package Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Point{
    int x,y,val;
    Point(int x,int y,int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class VerticalOrderTraversalOfBinaryTree {
	
	TreeNode root;

	public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) { 
        	return res;
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<Point>(1005,new Comparator<Point>(){
            public int compare(Point p1,Point p2) {
                if(p1.x < p2.x) return -1;
                if(p2.x < p1.x) return 1;
                if(p1.y > p2.y) return -1;
                if(p1.y < p2.y) return 1;
                return p1.val - p2.val;
            }
        });
        
        System.out.println("root: "+root.val);
        
        dfs(root, 0, 0, pq);
        int prev_x = Integer.MIN_VALUE;
        
        while(!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.x > prev_x) {
                List<Integer> list = new ArrayList();
                list.add(p.val);
                res.add(list);
            }
            else{
                List<Integer> list = res.get(res.size()-1);
                list.add(p.val);
            }
            prev_x = p.x;
        }
        return res;
    }
    
    private static void dfs(TreeNode root, int x, int y, PriorityQueue<Point> pq) {
        if(root == null) { 
        	return;
        }
        System.out.println("root: "+root.val+" pq: "+pq);
        
        pq.offer(new Point(x, y, root.val));
        dfs(root.left, x-1, y-1, pq);
        dfs(root.right, x+1, y-1, pq);
    }
	
	public static void main(String[] args) {
		VerticalOrderTraversalOfBinaryTree tree = new VerticalOrderTraversalOfBinaryTree();
		/*
	 			    4
			       / \
			      2   7
			     / \
			    1   3
		*/

		tree.root = new TreeNode(3);
		tree.root.left = new TreeNode(9);
		tree.root.right = new TreeNode(20);
		tree.root.right.right = new TreeNode(7);
		tree.root.right.left = new TreeNode(15);
		
		verticalTraversal(tree.root);
	}
}