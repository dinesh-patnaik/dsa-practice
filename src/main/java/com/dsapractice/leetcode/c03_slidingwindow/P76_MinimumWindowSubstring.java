package com.dsapractice.leetcode.c03_slidingwindow;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [76] MinimumWindowSubstring
 * Difficulty: Hard
 * Source: https://leetcode.com/problems/minimum-window-substring/
 * Status: To Do
 */
public class P76_MinimumWindowSubstring {

    public static class Solution {
        public String minWindow(String s, String t) {
            // TODO: Implement solution
            return "";
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P76_MinimumWindowSubstring.class);
        Solution solution = new Solution();
        String s = "";
        String t = "";
        var result = solution.minWindow(s, t);
        System.out.println("Result: " + result);
    }
}
