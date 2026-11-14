package com.dsapractice.leetcode.common;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Standard binary tree node definition used across LeetCode problems.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Helper to construct a binary tree from level-order array with nulls.
     * e.g., TreeNode.of(1, null, 2, 3)
     */
    public static TreeNode of(Integer... values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode curr = queue.poll();

            if (i < values.length && values[i] != null) {
                curr.left = new TreeNode(values[i]);
                queue.offer(curr.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                curr.right = new TreeNode(values[i]);
                queue.offer(curr.right);
            }
            i++;
        }

        return root;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TreeNode other)) return false;
        return this.val == other.val &&
                Objects.equals(this.left, other.left) &&
                Objects.equals(this.right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }
}
