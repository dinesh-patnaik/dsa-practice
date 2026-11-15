package com.dsapractice.leetcode.c03_slidingwindow;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [3] LongestSubstringWithoutRepeatingCharacters
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Status: To Do
 */
public class P3_LongestSubstringWithoutRepeatingCharacters {

    public static class Solution {
        public int lengthOfLongestSubstring(String s) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P3_LongestSubstringWithoutRepeatingCharacters.class);
        Solution solution = new Solution();
        String s = "";
        var result = solution.lengthOfLongestSubstring(s);
        System.out.println("Result: " + result);
    }
}
