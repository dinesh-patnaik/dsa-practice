package com.dsapractice.leetcode.c05_binarysearch;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [69] Sqrt(x)
 * Difficulty: Easy
 * Category: Binary Search
 * Source: https://leetcode.com/problems/sqrtx/
 * Status: To Do
 */
public class P69_SqrtX {

    public static class Solution {
        public int mySqrt(int x) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P69_SqrtX.class);
        Solution solution = new Solution();
        int x = 8;
        int result = solution.mySqrt(x);
        System.out.println("sqrt(" + x + ") = " + result);
    }
}
