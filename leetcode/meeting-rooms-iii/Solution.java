// https://leetcode.com/problems/meeting-rooms-iii/

class Solution {

    class Assignment {
        int room;
        long endTime;

        public Assignment(int room, long endTime) {
            this.room = room;
            this.endTime = endTime;
        }
    }

    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int[] meetingCounts = new int[n];
        Queue<Assignment> usedRooms = new PriorityQueue<>((a,b) -> a.endTime != b.endTime ? Long.compare(a.endTime,b .endTime) : Long.compare(a.room, b.room));
        Queue<Integer> unusedRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            unusedRooms.offer(i);
        }

        for (int[] meeting: meetings) {
            int start = meeting[0];
            int end = meeting[1];

            while (!usedRooms.isEmpty() && usedRooms.peek().endTime <= start) {
                Assignment a = usedRooms.poll();
                unusedRooms.offer(a.room);
            }

            if (!unusedRooms.isEmpty()) {
                int room = unusedRooms.poll();
                usedRooms.offer(new Assignment(room, end));
                meetingCounts[room]++;
            } else {
                long nextRoomTime = usedRooms.peek().endTime;
                Assignment a = usedRooms.poll();
                int room = a.room;
                usedRooms.offer(new Assignment(room, nextRoomTime + end - start));
                meetingCounts[room]++;
            }
        }

        int maxMeetingCount = 0, maxMeetingCountRoom = 0;
        for (int i = 0; i < n; i++) {
            if (meetingCounts[i] > maxMeetingCount) {
                maxMeetingCount = meetingCounts[i];
                maxMeetingCountRoom = i;
            }
        }

        return maxMeetingCountRoom;
    }
}