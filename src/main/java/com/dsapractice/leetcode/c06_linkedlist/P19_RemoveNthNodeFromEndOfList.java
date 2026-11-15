package com.dsapractice.leetcode.c06_linkedlist;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.ListNode;

/**
 * [19] RemoveNthNodeFromEndOfList
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Status: To Do
 */
public class P19_RemoveNthNodeFromEndOfList {

    public static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // TODO: Implement solution
            return null;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P19_RemoveNthNodeFromEndOfList.class);
        Solution solution = new Solution();
        ListNode head = null;
        int n = 0;
        var result = solution.removeNthFromEnd(head, n);
        System.out.println("Result: " + result);
    }
}
