package com.dsapractice.leetcode.c11_graphs;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [323] NumberOfConnectedComponentsInUndirectedGraph
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * Status: To Do
 */
public class P323_NumberOfConnectedComponentsInUndirectedGraph {

    public static class Solution {
        public int countComponents(int n, int[][] edges) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P323_NumberOfConnectedComponentsInUndirectedGraph.class);
        Solution solution = new Solution();
        int n = 0;
        int[][] edges = new int[][]{};
        var result = solution.countComponents(n, edges);
        System.out.println("Result: " + result);
    }
}
