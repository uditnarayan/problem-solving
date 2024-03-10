//https://leetcode.com/problems/reverse-nodes-in-k-group

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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = null;
        ListNode ptr = head;
        ListNode tail = null;
        while (ptr != null) {
            int count = 0;
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count++;
            }

            if (count == k) {
                ListNode revHead = reverseLinkedList(head, k);

                if (newHead == null) {
                    newHead = revHead;
                }

                if (tail != null) {
                    tail.next = revHead;
                }

                tail = head;
                head = ptr;
            }
        }
        if (tail != null)
            tail.next = head;

        return newHead;
    }

    public ListNode reverseLinkedList(ListNode head, int k) {
        ListNode prev= null;
        ListNode current= head;
        ListNode next= head;
        while (k > 0) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            k--;
        }
        return prev;
    }
}
