package com.dsapractice.leetcode.c03_slidingwindow;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class P1423_MaximumPointsYouCanObtainFromCardsTest {

    private final P1423_MaximumPointsYouCanObtainFromCards.Solution solution = new P1423_MaximumPointsYouCanObtainFromCards.Solution();

    @Test
    @Disabled("Enable when solution is implemented")
    @DisplayName("Example 1")
    void testExample1() {
        assertEquals(12, solution.maxScore(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 3));
    }

    @Test
    @Disabled("Enable when solution is implemented")
    @DisplayName("Example 2")
    void testExample2() {
        assertEquals(4, solution.maxScore(new int[] { 2, 2, 2 }, 2));
    }

    @Test
    @Disabled("Enable when solution is implemented")
    @DisplayName("Example 3 - take all cards")
    void testExample3() {
        assertEquals(55, solution.maxScore(new int[] { 9, 7, 7, 9, 7, 7, 9 }, 7));
    }
}
