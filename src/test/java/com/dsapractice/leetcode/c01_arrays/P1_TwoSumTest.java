package com.dsapractice.leetcode.c01_arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class P1_TwoSumTest {

    private final P1_TwoSum.Solution solution = new P1_TwoSum.Solution();

    @Test
    @DisplayName("Example 1: nums = [2,7,11,15], target = 9")
    void testExample1() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        assertArrayEquals(new int[]{0, 1}, solution.twoSum(nums, target));
    }
}
