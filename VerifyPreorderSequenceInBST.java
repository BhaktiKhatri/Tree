package Tree;

import java.util.Arrays;

public class VerifyPreorderSequenceInBST {

	public static boolean verifyPreorder(int[] preorder) {
	    int low = Integer.MIN_VALUE;
	    int i = -1;
	    
	    System.out.println("preorder: "+Arrays.toString(preorder));
	    
	    for(int p : preorder) {
	    	System.out.println("p: "+p+" low: "+low);
	    	
	        if (p < low)
	            return false;
	       
	        while (i >= 0 && p > preorder[i]) {
	        	System.out.println("i: "+i+" p: "+p+" preorder[i]: "+preorder[i]+" low: "+low);
	            low = preorder[i];
	            i--;
	            System.out.println("low: "+low+" i: "+i);
	        }
	        System.out.println("i: "+i+" p: "+p);
	        
	        ++i;
	        preorder[i] = p;
	        System.out.println("i: "+i+" p: "+p+" preorder[i]: "+preorder[i]);
	    }
	    System.out.println("preorder: "+Arrays.toString(preorder));
	    
	    return true;
	}
	
	public static void main(String[] args) {
		int[] preorder = {20, 8, 4, 12, 10, 14, 22};
		System.out.println(verifyPreorder(preorder));
	}

}
