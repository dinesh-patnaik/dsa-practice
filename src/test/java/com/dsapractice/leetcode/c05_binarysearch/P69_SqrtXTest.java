package com.dsapractice.leetcode.c05_binarysearch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class P69_SqrtXTest {

    private final P69_SqrtX.Solution solution = new P69_SqrtX.Solution();

    @Test
    @Disabled("Enable when solution is implemented")
    @DisplayName("Example 1: x = 4 -> 2")
    void testExample1() {
        assertEquals(2, solution.mySqrt(4));
    }

    @Test
    @Disabled("Enable when solution is implemented")
    @DisplayName("Example 2: x = 8 -> 2")
    void testExample2() {
        assertEquals(2, solution.mySqrt(8));
    }
}
