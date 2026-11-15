package com.dsapractice.leetcode.c14_2d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [62] UniquePaths
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/unique-paths/
 * Status: To Do
 */
public class P62_UniquePaths {

    public static class Solution {
        public int uniquePaths(int m, int n) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P62_UniquePaths.class);
        Solution solution = new Solution();
        int m = 0;
        int n = 0;
        var result = solution.uniquePaths(m, n);
        System.out.println("Result: " + result);
    }
}
