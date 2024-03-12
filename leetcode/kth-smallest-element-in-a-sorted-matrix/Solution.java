// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

class Solution {
    static class Node {
        int num;
        int row;
        int col;
        public Node(int n, int r, int c) {
            num = n;
            row = r;
            col = c;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        Queue<Node> minHeap = new PriorityQueue<>((a,b) -> a.num - b.num);
        for (int r = 0; r < n ; r++) {
            minHeap.offer(new Node(matrix[r][0], r, 0));
        }   
        Node node = minHeap.peek();
        while (k > 0) {
            node = minHeap.poll();
            int r = node.row;
            int c = node.col;
            if (c < n - 1) {
                minHeap.offer(new Node(matrix[r][c+1],r, c+1));
            }
            k--;
        }
        return node.num;
    }
}