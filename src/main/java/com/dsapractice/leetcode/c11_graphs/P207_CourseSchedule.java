package com.dsapractice.leetcode.c11_graphs;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [207] CourseSchedule
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/course-schedule/
 * Status: To Do
 */
public class P207_CourseSchedule {

    public static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // TODO: Implement solution
            return false;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P207_CourseSchedule.class);
        Solution solution = new Solution();
        int numCourses = 0;
        int[][] prerequisites = new int[][]{};
        var result = solution.canFinish(numCourses, prerequisites);
        System.out.println("Result: " + result);
    }
}
