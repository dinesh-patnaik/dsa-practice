package com.dsapractice.leetcode.c01_arrays;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [242] ValidAnagram
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/valid-anagram/
 * Status: To Do
 */
public class P242_ValidAnagram {

    public static class Solution {
        public boolean isAnagram(String s, String t) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P242_ValidAnagram.class);
        Solution solution = new Solution();
        String s = "";
        String t = "";
        var result = solution.isAnagram(s, t);
        System.out.println("Result: " + result);
    }
}
