package com.dsapractice.leetcode.c05_binarysearch;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [33] SearchInRotatedSortedArray
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Status: To Do
 */
public class P33_SearchInRotatedSortedArray {

    public static class Solution {
        public int search(int[] nums, int target) {
            // TODO: Implement solution
            return -1;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P33_SearchInRotatedSortedArray.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        int target = 0;
        var result = solution.search(nums, target);
        System.out.println("Result: " + result);
    }
}
