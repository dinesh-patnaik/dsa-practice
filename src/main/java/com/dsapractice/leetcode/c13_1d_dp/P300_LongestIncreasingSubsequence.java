package com.dsapractice.leetcode.c13_1d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [300] LongestIncreasingSubsequence
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/longest-increasing-subsequence/
 * Status: To Do
 */
public class P300_LongestIncreasingSubsequence {

    public static class Solution {
        public int lengthOfLIS(int[] nums) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P300_LongestIncreasingSubsequence.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        var result = solution.lengthOfLIS(nums);
        System.out.println("Result: " + result);
    }
}
