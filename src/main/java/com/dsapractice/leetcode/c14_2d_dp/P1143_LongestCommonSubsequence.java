package com.dsapractice.leetcode.c14_2d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [1143] LongestCommonSubsequence
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/longest-common-subsequence/
 * Status: To Do
 */
public class P1143_LongestCommonSubsequence {

    public static class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P1143_LongestCommonSubsequence.class);
        Solution solution = new Solution();
        String text1 = "";
        String text2 = "";
        var result = solution.longestCommonSubsequence(text1, text2);
        System.out.println("Result: " + result);
    }
}
