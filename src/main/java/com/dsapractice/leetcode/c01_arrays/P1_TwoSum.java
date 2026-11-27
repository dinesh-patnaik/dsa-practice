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

            int[] ans = new int[2];

            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sum == target) {
                    ans[1] = i;
                } else if (sum > target) {
                    sum -= ans[0];
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Output: " + java.util.Arrays.toString(result));
    }
}
