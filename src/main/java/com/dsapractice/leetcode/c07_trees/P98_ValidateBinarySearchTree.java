package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [98] ValidateBinarySearchTree
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/validate-binary-search-tree/
 * Status: To Do
 */
public class P98_ValidateBinarySearchTree {

    public static class Solution {
        public boolean isValidBST(TreeNode root) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P98_ValidateBinarySearchTree.class);
        Solution solution = new Solution();
        TreeNode root = null;
        var result = solution.isValidBST(root);
        System.out.println("Result: " + result);
    }
}
