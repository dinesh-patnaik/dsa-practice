package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [235] LowestCommonAncestorOfBST
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Status: To Do
 */
public class P235_LowestCommonAncestorOfBST {

    public static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // TODO: Implement solution
            return null;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P235_LowestCommonAncestorOfBST.class);
        Solution solution = new Solution();
        TreeNode root = null;
        TreeNode p = null;
        TreeNode q = null;
        var result = solution.lowestCommonAncestor(root, p, q);
        System.out.println("Result: " + result);
    }
}
