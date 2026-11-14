package com.dsapractice.leetcode.c06_linkedlist;

import com.dsapractice.leetcode.common.ListNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class P206_ReverseLinkedListTest {

    private final P206_ReverseLinkedList.Solution solution = new P206_ReverseLinkedList.Solution();

    @Test
    @DisplayName("Example 1: [1,2,3,4,5] -> [5,4,3,2,1]")
    void testExample1() {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        ListNode reversed = solution.reverseList(head);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reversed.toArray());
    }
}
