package com.dsapractice.leetcode.common;

import java.util.Objects;

/**
 * Standard singly-linked list node definition used across LeetCode problems.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * Helper to construct a linked list from an integer array.
     */
    public static ListNode of(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) {
            current.next = new ListNode(v);
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * Converts linked list to array for assertions.
     */
    public int[] toArray() {
        int length = 0;
        ListNode curr = this;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        int[] result = new int[length];
        curr = this;
        for (int i = 0; i < length; i++) {
            result[i] = curr.val;
            curr = curr.next;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ListNode other)) return false;
        return this.val == other.val && Objects.equals(this.next, other.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}
