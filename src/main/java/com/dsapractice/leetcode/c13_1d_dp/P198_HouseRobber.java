package com.dsapractice.leetcode.c13_1d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [198] HouseRobber
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/house-robber/
 * Status: To Do
 */
public class P198_HouseRobber {

    public static class Solution {
        public int rob(int[] nums) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P198_HouseRobber.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        var result = solution.rob(nums);
        System.out.println("Result: " + result);
    }
}
