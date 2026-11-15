package com.dsapractice.leetcode.c07_trees;

import com.dsapractice.leetcode.common.TrackerAutoSync;

import com.dsapractice.leetcode.common.TreeNode;

/**
 * [105] ConstructBinaryTreeFromPreorderAndInorder
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Status: To Do
 */
public class P105_ConstructBinaryTreeFromPreorderAndInorder {

    public static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // TODO: Implement solution
            return null;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P105_ConstructBinaryTreeFromPreorderAndInorder.class);
        Solution solution = new Solution();
        int[] preorder = new int[]{};
        int[] inorder = new int[]{};
        var result = solution.buildTree(preorder, inorder);
        System.out.println("Result: " + result);
    }
}
