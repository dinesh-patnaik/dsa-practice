package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [124] BinaryTreeMaximumPathSum
 * Difficulty: Hard
 * Source: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Status: To Do
 */
public class P124_BinaryTreeMaximumPathSum {

    public static class Solution {
        public int maxPathSum(TreeNode root) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P124_BinaryTreeMaximumPathSum.class);
        Solution solution = new Solution();
        TreeNode root = null;
        var result = solution.maxPathSum(root);
        System.out.println("Result: " + result);
    }
}
