package com.dsapractice.leetcode.c18_bit_manipulation;

/**
 * [477] TotalHammingDistance
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/total-hamming-distance/
 * Status: Done
 */
public class P477_TotalHammingDistance {

    public static class Solution {
        public int totalHammingDistance(int[] nums) {
            int ans = 0;

            for (int i = 0; i < 32; i++) {
                int set = 1 << i;
                int setCount = 0;
                for (int k = 0; k < nums.length; k++) {
                    if ((nums[k] & set) > 0) {
                        setCount++;
                    }
                }
                ans = ans + (setCount * (nums.length - setCount));
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = { 4, 14, 2 };
        var result = solution.totalHammingDistance(nums);
        System.out.println("Result: " + result); // Expected: 6
    }
}
