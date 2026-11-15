package com.dsapractice.leetcode.c01_arrays;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [347] TopKFrequentElements
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/top-k-frequent-elements/
 * Status: To Do
 */
public class P347_TopKFrequentElements {

    public static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // TODO: Implement solution
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P347_TopKFrequentElements.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        int k = 0;
        var result = solution.topKFrequent(nums, k);
        System.out.println("Result: " + java.util.Arrays.deepToString(new Object[]{result}));
    }
}
