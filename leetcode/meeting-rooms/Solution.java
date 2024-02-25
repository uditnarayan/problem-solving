// https://leetcode.com/problems/meeting-rooms/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (overlap(intervals[i], intervals[i+1])) {
                return false;
            }
        }
        return true;
    }

    private boolean overlap(int[] interval1, int[] interval2) {
        return interval1[0] < interval2[1] && interval2[0] < interval1[1];
    }
}