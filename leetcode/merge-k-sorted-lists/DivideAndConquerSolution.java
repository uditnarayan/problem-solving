// https://leetcode.com/problems/merge-k-sorted-lists/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        int n = lists.length();
        if (n > 0) {
            int step = 1;
            while(step < lists.size()) {
                for (int i = 0; i < n - step; i *= step) {
                    lists[i] = merge2Lists(lists[i], lists[i+step]);
                }
                step *= 2;
            }
        }
        return lists[0];
    }

    public ListNode merge2Lists(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(-1);
        ListNode current = head;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }

        current.next = head1 == null ? head2 : head1;
        return head;
    }
}