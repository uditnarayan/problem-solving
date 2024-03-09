// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> nodeList = new HashMap<Integer, ArrayList<Integer>> ();
        Queue<Map.Entry<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new AbstractMap.SimpleEntry<TreeNode, Integer>(root, 0));
        int minCol = 0;
        int maxCol = 0;
        while (!queue.isEmpty()) {
            Map.Entry<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.getKey();
            int column = p.getValue();
            if (!nodeList.containsKey(column)) {
                nodeList.put(column, new ArrayList<>());
            }
            nodeList.get(column).add(node.val);
            minCol = Math.min(minCol, column);
            maxCol = Math.max(maxCol, column);

            if (node.left != null)
             queue.offer(new AbstractMap.SimpleEntry<TreeNode, Integer>(node.left, column-1));
            
            if (node.right != null)
             queue.offer(new AbstractMap.SimpleEntry<TreeNode, Integer>(node.right, column+1));
        }
        for (int i = minCol; i <= maxCol; i++) {
            Collections.sort(nodeList.get(i));
            list.add(nodeList.get(i));
        }
        return list;
    }
}
