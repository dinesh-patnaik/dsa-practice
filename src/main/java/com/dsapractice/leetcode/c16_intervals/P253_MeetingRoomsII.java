package com.dsapractice.leetcode.c16_intervals;

import com.dsapractice.leetcode.common.TrackerAutoSync;

/**
 * [253] MeetingRoomsII
 * Difficulty: Medium
 * Source: https://leetcode.com/problems/meeting-rooms-ii/
 * Status: To Do
 */
public class P253_MeetingRoomsII {

    public static class Solution {
        public int minMeetingRooms(int[][] intervals) {
            // TODO: Implement solution
            return 0;
        }
    }

    public static void main(String[] args) {
        TrackerAutoSync.sync(P253_MeetingRoomsII.class);
        Solution solution = new Solution();
        int[][] intervals = new int[][]{};
        var result = solution.minMeetingRooms(intervals);
        System.out.println("Result: " + result);
    }
}
