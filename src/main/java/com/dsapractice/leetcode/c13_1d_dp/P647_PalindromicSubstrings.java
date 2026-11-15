package com.dsapractice.leetcode.c13_1d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [647] PalindromicSubstrings
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/palindromic-substrings/
 * Status: To Do
 */
public class P647_PalindromicSubstrings {

    public static class Solution {
        public int countSubstrings(String s) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P647_PalindromicSubstrings.class);
        Solution solution = new Solution();
        String s = "";
        var result = solution.countSubstrings(s);
        System.out.println("Result: " + result);
    }
}
