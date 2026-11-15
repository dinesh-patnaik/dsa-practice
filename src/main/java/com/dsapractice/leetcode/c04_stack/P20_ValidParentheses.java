package com.dsapractice.leetcode.c04_stack;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [20] ValidParentheses
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/valid-parentheses/
 * Status: To Do
 */
public class P20_ValidParentheses {

    public static class Solution {
        public boolean isValid(String s) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P20_ValidParentheses.class);
        Solution solution = new Solution();
        String s = "";
        var result = solution.isValid(s);
        System.out.println("Result: " + result);
    }
}
