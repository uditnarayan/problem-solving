// https://leetcode.com/problems/meeting-rooms-ii/description/

import java.util.*;

class Solution {

    class Assignment {
        int room;
        int endTime;

        public Assignment(int room, int endTime) {
            this.room = room;
            this.endTime = endTime;
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null && intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0], b[0]));
        int nextRoomNumber = 0;
        int emptyRooms = 0;
        Queue<Assignment> assignedRooms = new PriorityQueue<>((a,b) -> Integer.compare(a.endTime, b.endTime));

        int index = 0;
        int time = intervals[index][0];
        while (index < intervals.length) {

            while (!assignedRooms.isEmpty() && time >= assignedRooms.peek().endTime) {
                Assignment a = assignedRooms.poll();
                emptyRooms++;
            }

            int nextIntervalTime = intervals[index][0];
            if (time == nextIntervalTime) {
                int endTime = intervals[index][1];
                int roomNumber = emptyRooms == 0 ? ++nextRoomNumber : emptyRooms--;
                assignedRooms.offer(new Assignment(nextRoomNumber, endTime));
                index++;
            }
            time = index == intervals.length ? intervals[index-1][1]: intervals[index][0];
        }
        return nextRoomNumber;
    }
}