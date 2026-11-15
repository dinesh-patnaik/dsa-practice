package com.dsapractice.leetcode.c18_bit_manipulation;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [338] CountingBits
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/counting-bits/
 * Status: To Do
 */
public class P338_CountingBits {

    public static class Solution {
        public int[] countBits(int n) {
            // TODO: Implement solution
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P338_CountingBits.class);
        Solution solution = new Solution();
        int n = 0;
        var result = solution.countBits(n);
        System.out.println("Result: " + java.util.Arrays.deepToString(new Object[]{result}));
    }
}
