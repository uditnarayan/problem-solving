// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/

class Solution {
    static class Node {
        int value;
        int i;
        int j;
        public Node(int value, int i, int j) {
            this.value =value;
            this.i = i;
            this.j = j;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        List<List<Integer>> answer = new ArrayList<>();
        Queue<Node> minHeap = new PriorityQueue<>((a,b) -> a.value - b.value);
        for (int i = 0; i < m; i++) {
            minHeap.offer(new Node(nums1[i] + nums2[0], i, 0));
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int i = node.i;
            int j = node.j;
            answer.add(List.of(nums1[i], nums2[j]));
            j++;
            if (j < n) {
                minHeap.offer(new Node(nums1[i] + nums2[j], i, j));
            }
        }
        return answer;
    }
}