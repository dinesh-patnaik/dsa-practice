package com.dsapractice.leetcode.c03_slidingwindow;

/**
 * [1423] MaximumPointsYouCanObtainFromCards
 * Difficulty: Medium
 * Source:
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 * Status: To Do
 */
public class P1423_MaximumPointsYouCanObtainFromCards {

    public static class Solution {
        public int maxScore(int[] A, int B) {
            int[] prefix = new int[A.length];
            int[] suffix = new int[A.length];

            prefix[0] = A[0];
            suffix[A.length - 1] = A[A.length - 1];

            for (int i = 1; i < A.length; i++) {
                prefix[i] = prefix[i - 1] + A[i];
            }

            for (int j = A.length - 2; j >= 0; j--) {
                suffix[j] = A[j] + suffix[j + 1];
            }

            int max = 0;
            max = findMax(prefix[B - 1], suffix[A.length - B]);

            int i = 0;
            while (i < B - 1) {
                int sum = prefix[i] + suffix[A.length - B + i + 1];
                max = findMax(max, sum);
                i++;
            }

            return max;
        }

        public int findMax(int a, int b) {
            return (a > b) ? a : b;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] cardPoints = new int[] { 2, 3, -1, 4, 2, 1 };
        int k = 4;
        var result = solution.maxScore(cardPoints, k);
        System.out.println("Result: " + result);
    }
}
