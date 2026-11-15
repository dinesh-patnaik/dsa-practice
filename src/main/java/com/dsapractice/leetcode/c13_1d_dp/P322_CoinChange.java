package com.dsapractice.leetcode.c13_1d_dp;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [322] CoinChange
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/coin-change/
 * Status: To Do
 */
public class P322_CoinChange {

    public static class Solution {
        public int coinChange(int[] coins, int amount) {
            // TODO: Implement solution
            return -1;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P322_CoinChange.class);
        Solution solution = new Solution();
        int[] coins = new int[]{};
        int amount = 0;
        var result = solution.coinChange(coins, amount);
        System.out.println("Result: " + result);
    }
}
