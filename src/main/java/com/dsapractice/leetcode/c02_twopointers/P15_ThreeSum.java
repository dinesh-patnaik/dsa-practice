package com.dsapractice.leetcode.c02_twopointers;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [15] ThreeSum
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/3sum/
 * Status: To Do
 */
public class P15_ThreeSum {

    public static class Solution {
        public java.util.List<java.util.List<Integer>> threeSum(int[] nums) {
            // TODO: Implement solution
            return java.util.Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P15_ThreeSum.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        var result = solution.threeSum(nums);
        System.out.println("Result: " + result);
    }
}
