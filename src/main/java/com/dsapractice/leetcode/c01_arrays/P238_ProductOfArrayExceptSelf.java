package com.dsapractice.leetcode.c01_arrays;

/**
 * [238] ProductOfArrayExceptSelf
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/product-of-array-except-self/
 * Status: To Do
 */
public class P238_ProductOfArrayExceptSelf {

    public static class Solution {
        public int[] productExceptSelf(int[] A) {
            int[] prefix = new int[A.length];
            int[] suffix = new int[A.length];
            int[] ans = new int[A.length];

            prefix[0] = A[0];
            suffix[A.length - 1] = A[A.length - 1];

            for (int i = 1; i < A.length; i++) {
                prefix[i] = prefix[i - 1] * prefix[i];
            }

            for (int j = A.length - 2; j >= 0; j--) {
                suffix[j] = suffix[j] * suffix[j + 1];
            }

            for (int k = 0; k < A.length; k++) {
                if (k == 0) {
                    ans[k] = suffix[k + 1];
                } else if (k == A.length - 1) {
                    ans[k] = prefix[k - 1];
                } else {
                    ans[k] = prefix[k - 1] * suffix[k + 1];
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        var result = solution.productExceptSelf(nums);
        System.out.println("Result: " + java.util.Arrays.deepToString(new Object[] { result }));
    }
}
