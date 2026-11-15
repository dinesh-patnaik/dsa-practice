package com.dsapractice.leetcode.c02_twopointers;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [125] ValidPalindrome
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/valid-palindrome/
 * Status: To Do
 */
public class P125_ValidPalindrome {

    public static class Solution {
        public boolean isPalindrome(String s) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P125_ValidPalindrome.class);
        Solution solution = new Solution();
        String s = "";
        var result = solution.isPalindrome(s);
        System.out.println("Result: " + result);
    }
}
