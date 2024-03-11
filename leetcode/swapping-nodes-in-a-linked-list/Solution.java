// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/

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
    public ListNode swapNodes(ListNode head, int k) {
        int len = 0;
        ListNode front = null;
        ListNode end = null;
        ListNode current = head;
        while (current != null) {
            len++;
            if (end != null) {
                end = end.next;
            }
            if (len == k) {
                front = current;
                end = head;
            }
            current = current.next;
        }
        int temp = front.val;
        front.val = end.val;
        end.val = temp;
        return head;
    }
}