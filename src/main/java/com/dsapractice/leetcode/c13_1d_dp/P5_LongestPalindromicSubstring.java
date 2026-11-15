package com.dsapractice.leetcode.c13_1d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [5] LongestPalindromicSubstring
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/longest-palindromic-substring/
 * Status: To Do
 */
public class P5_LongestPalindromicSubstring {

    public static class Solution {
        public String longestPalindrome(String s) {
            // TODO: Implement solution
            return "";
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P5_LongestPalindromicSubstring.class);
        Solution solution = new Solution();
        String s = "";
        var result = solution.longestPalindrome(s);
        System.out.println("Result: " + result);
    }
}
