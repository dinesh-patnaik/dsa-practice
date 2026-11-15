package com.dsapractice.leetcode.c01_arrays;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [128] LongestConsecutiveSequence
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/longest-consecutive-sequence/
 * Status: To Do
 */
public class P128_LongestConsecutiveSequence {

    public static class Solution {
        public int longestConsecutive(int[] nums) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P128_LongestConsecutiveSequence.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        var result = solution.longestConsecutive(nums);
        System.out.println("Result: " + result);
    }
}
