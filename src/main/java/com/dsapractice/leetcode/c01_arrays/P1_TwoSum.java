package com.dsapractice.leetcode.c01_arrays;

/**
 * [1] TwoSum
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/two-sum/
 * Status: To Do
 */
public class P1_TwoSum {

    public static class Solution {
        public int[] twoSum(int[] nums, int target) {
            // TODO: implement
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Output: " + java.util.Arrays.toString(result));
    }
}
