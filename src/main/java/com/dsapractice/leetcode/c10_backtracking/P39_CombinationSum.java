package com.dsapractice.leetcode.c10_backtracking;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [39] CombinationSum
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/combination-sum/
 * Status: To Do
 */
public class P39_CombinationSum {

    public static class Solution {
        public java.util.List<java.util.List<Integer>> combinationSum(int[] candidates, int target) {
            // TODO: Implement solution
            return java.util.Collections.emptyList();
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P39_CombinationSum.class);
        Solution solution = new Solution();
        int[] candidates = new int[]{};
        int target = 0;
        var result = solution.combinationSum(candidates, target);
        System.out.println("Result: " + result);
    }
}
