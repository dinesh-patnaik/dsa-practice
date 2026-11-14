package com.dsapractice.leetcode.c06_linkedlist;

import com.dsapractice.leetcode.common.ListNode;

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
}
