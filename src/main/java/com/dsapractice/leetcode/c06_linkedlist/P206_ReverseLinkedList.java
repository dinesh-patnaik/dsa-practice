package com.dsapractice.leetcode.c06_linkedlist;

import com.dsapractice.leetcode.common.ListNode;

/**
 * [206] ReverseLinkedList
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/reverse-linked-list/
 * Status: To Do
 */
public class P206_ReverseLinkedList {

    public static class Solution {
        public ListNode reverseList(ListNode head) {
            // TODO: implement
            return null;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        ListNode reversed = solution.reverseList(head);
        System.out.println("Reversed List: " + java.util.Arrays.toString(reversed.toArray()));
    }
}
