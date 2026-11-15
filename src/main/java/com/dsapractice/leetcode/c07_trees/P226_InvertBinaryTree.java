package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [226] InvertBinaryTree
 * Difficulty: Easy
 * Source: https://leetcode.com/problems/invert-binary-tree/
 * Status: To Do
 */
public class P226_InvertBinaryTree {

    public static class Solution {
        public TreeNode invertTree(TreeNode root) {
            // TODO: Implement solution
            return null;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P226_InvertBinaryTree.class);
        Solution solution = new Solution();
        TreeNode root = null;
        var result = solution.invertTree(root);
        System.out.println("Result: " + result);
    }
}
