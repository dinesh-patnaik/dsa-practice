package com.dsapractice.leetcode.c05_binarysearch;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [704] BinarySearch
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/binary-search/
 * Status: To Do
 */
public class P704_BinarySearch {

    public static class Solution {
        public int search(int[] nums, int target) {
            // TODO: Implement solution
            return -1;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P704_BinarySearch.class);
        Solution solution = new Solution();
        int[] nums = new int[]{};
        int target = 0;
        var result = solution.search(nums, target);
        System.out.println("Result: " + result);
    }
}
