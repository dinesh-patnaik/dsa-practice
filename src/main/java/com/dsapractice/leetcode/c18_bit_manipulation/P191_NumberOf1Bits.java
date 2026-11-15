package com.dsapractice.leetcode.c18_bit_manipulation;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [191] NumberOf1Bits
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/number-of-1-bits/
 * Status: Done
 */
public class P191_NumberOf1Bits {

    public static class Solution {
        public int hammingWeight(int n) {
            int ans = 0;
            while (n > 0) {
                if (n % 2 == 1) {
                    ans++;
                }
                n = n / 2;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P191_NumberOf1Bits.class);
        Solution solution = new Solution();
        int n = 6;
        var result = solution.hammingWeight(n);
        System.out.println("Result: " + result);
    }
}
