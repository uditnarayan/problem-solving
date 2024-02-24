// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

class IterativeSolution {
	public TreeNode bstFromPreorder(int[] preorder) {
        Stack<TreeNode> stack = new Stack<>();
		TreeNode root = new TreeNode(preorder[0]);
		stack.push(root);
		for (int i = 1; i < preorder.length; i++) {
			int next = preorder[i];
			TreeNode node = stack.peek();
			while(!stack.isEmpty() && stack.peek().val < next) {
				node = stack.pop();
			}

			TreeNode child = new TreeNode(next);
			if (node.val < next) {
				node.right = child;
			} else {
				node.left = child;
			}
			stack.push(child);
		}
		return root;
    }
}