class Solution { 

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        Queue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.offer(lists[i]);
        }

        ListNode current = head;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        return head.next;
    }
}