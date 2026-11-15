package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [100] SameTree
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/same-tree/
 */
public class P100_SameTree {

    public static class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode p = null;
        TreeNode q = null;
        var result = solution.isSameTree(p, q);
        System.out.println("Result: " + result);
    }
}
