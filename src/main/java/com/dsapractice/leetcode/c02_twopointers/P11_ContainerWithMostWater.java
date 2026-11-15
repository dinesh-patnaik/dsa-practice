package com.dsapractice.leetcode.c02_twopointers;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [11] ContainerWithMostWater
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/container-with-most-water/
 * Status: To Do
 */
public class P11_ContainerWithMostWater {

    public static class Solution {
        public int maxArea(int[] height) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P11_ContainerWithMostWater.class);
        Solution solution = new Solution();
        int[] height = new int[]{};
        var result = solution.maxArea(height);
        System.out.println("Result: " + result);
    }
}
