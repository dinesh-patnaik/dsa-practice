package com.dsapractice.leetcode.c11_graphs;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [200] NumberOfIslands
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/number-of-islands/
 * Status: To Do
 */
public class P200_NumberOfIslands {

    public static class Solution {
        public int numIslands(char[][] board) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P200_NumberOfIslands.class);
        Solution solution = new Solution();
        char[][] board = new char[][]{};
        var result = solution.numIslands(board);
        System.out.println("Result: " + result);
    }
}
