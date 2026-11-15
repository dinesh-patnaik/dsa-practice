package com.dsapractice.leetcode.c15_greedy;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [53] MaximumSubarray
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/maximum-subarray/
 * Status: To Do
 */
public class P53_MaximumSubarray {

    public static class Solution {
        public int maxSubArray(int[] nums) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P53_MaximumSubarray.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        var result = solution.maxSubArray(nums);
        System.out.println("Result: " + result);
    }
}
