// https://leetcode.com/problems/insert-interval

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> list = new LinkedList<>();
        int i = 0;
        int n = intervals.length;
        while (i < n && intervals[i][0] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        if (list.size() == 0 || list.getLast()[1] < newInterval[0]) {
            list.add(newInterval);
        } else {
            list.getLast()[1] = Math.max(list.getLast()[1], newInterval[1]);
        }

        while (i < n) {
            if (list.getLast()[1] < intervals[i][0]) {
                list.add(intervals[i]);
            } else {
                list.getLast()[1] = Math.max(list.getLast()[1], intervals[i][1]);
            }
            i++;
        }
        return list.toArray(new int[list.size()][]);
    }
}
