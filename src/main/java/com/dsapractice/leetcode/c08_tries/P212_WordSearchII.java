package com.dsapractice.leetcode.c08_tries;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [212] WordSearchII
 * Difficulty: Hard
 * Source: https://leetcode.com/problems/word-search-ii/
 * Status: To Do
 */
public class P212_WordSearchII {

    public static class Solution {
        public java.util.List<String> findWords(char[][] board, String[] words) {
            // TODO: Implement solution
            return java.util.Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P212_WordSearchII.class);
        Solution solution = new Solution();
        char[][] board = new char[][]{};
        String[] words = new String[]{};
        var result = solution.findWords(board, words);
        System.out.println("Result: " + result);
    }
}
