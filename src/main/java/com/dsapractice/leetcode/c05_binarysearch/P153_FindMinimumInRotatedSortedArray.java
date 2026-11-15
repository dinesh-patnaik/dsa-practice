package com.dsapractice.leetcode.c05_binarysearch;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [153] FindMinimumInRotatedSortedArray
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Status: To Do
 */
public class P153_FindMinimumInRotatedSortedArray {

    public static class Solution {
        public int findMin(int[] nums) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P153_FindMinimumInRotatedSortedArray.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        var result = solution.findMin(nums);
        System.out.println("Result: " + result);
    }
}
