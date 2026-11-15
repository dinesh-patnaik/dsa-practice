package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [572] SubtreeOfAnotherTree
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/subtree-of-another-tree/
 * Status: To Do
 */
public class P572_SubtreeOfAnotherTree {

    public static class Solution {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P572_SubtreeOfAnotherTree.class);
        Solution solution = new Solution();
        TreeNode root = null;
        TreeNode subRoot = null;
        var result = solution.isSubtree(root, subRoot);
        System.out.println("Result: " + result);
    }
}
