package com.dsapractice.leetcode.c03_slidingwindow;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [424] LongestRepeatingCharacterReplacement
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/longest-repeating-character-replacement/
 * Status: To Do
 */
public class P424_LongestRepeatingCharacterReplacement {

    public static class Solution {
        public int characterReplacement(String s, int k) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P424_LongestRepeatingCharacterReplacement.class);
        Solution solution = new Solution();
        String s = "";
        int k = 0;
        var result = solution.characterReplacement(s, k);
        System.out.println("Result: " + result);
    }
}
