package com.dsapractice.leetcode.c06_linkedlist;

import com.dsapractice.leetcode.common.ListNode;
import java.util.Arrays;

/**
 * [206] ReverseLinkedList
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/reverse-linked-list/
 */
public class P206_ReverseLinkedList {

    public static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        ListNode reversed = solution.reverseList(head);
        System.out.println("Reversed List: " + Arrays.toString(reversed.toArray()));
    }
}
