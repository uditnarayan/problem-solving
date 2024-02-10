//	https://leetcode.com/problems/binary-tree-vertical-order-traversal/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }



class Solution {

	class TreeNodeColumn {
		TreeNode node;
		int column;

		TreeNodeColumn(TreeNode node, int column) {
			this.node = node;
			this.column = column;
		}
	}


    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNodeColumn> queue = new LinkedList<>();
        queue.offer(new TreeNodeColumn(root, 0));

        // We can use min and max columns to keep track for 
        // min max to avoid sorting of keys of map to calculate output
        
        int minColumn = 0;
        int maxColumn = 0;
        while(!queue.isEmpty()) {
        	TreeNodeColumn nodeColumn = queue.poll();
        	TreeNode node = nodeColumn.node;

        	int column = nodeColumn.column;
        	map.computeIfAbsent(column, val-> new ArrayList<>()).add(node.val);
        	minColumn = Math.min(minColumn, column);
        	maxColumn = Math.max(maxColumn, column);

        	if (node.left != null) {
        		queue.offer(new TreeNodeColumn(node.left, column - 1));
        	}

        	if (node.right != null) {
        		queue.offer(new TreeNodeColumn(node.right, column + 1));
        	}
        }
        
        for (int i = minColumn; i <= maxColumn; i++) {
        	if (map.containsKey(i)) 
        		output.add(map.get(i));
        }
        return output;
    }
}