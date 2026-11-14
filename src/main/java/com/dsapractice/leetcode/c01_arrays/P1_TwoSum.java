package com.dsapractice.leetcode.c01_arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * [1] TwoSum
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/two-sum/
 */
public class P1_TwoSum {

    public static class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(complement), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}
