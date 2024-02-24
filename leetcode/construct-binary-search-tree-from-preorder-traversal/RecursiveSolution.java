class RecursiveSolution {
	private int currentIdx;
    public TreeNode bstFromPreorder(int[] preorder) {
        currentIdx = 0;
        return bstFromPreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int[] preorder, int lower, int upper) {
    	if (currentIdx == preorder.length) {
    		return null;
    	}

    	int val = preorder[currentIdx];
        System.out.println(val + " " + lower + " " + upper);
    	if (val < lower || val > upper) {
    		return null;
    	}

    	TreeNode node = new TreeNode(val);
    	currentIdx++;
    	node.left = bstFromPreorder(preorder, lower, val);
    	node.right = bstFromPreorder(preorder, val, upper);
    	return node;
    }
}