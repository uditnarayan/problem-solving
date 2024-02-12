import java.util.*;

class Appointment {
	int start;
	int duration;

	public Appointment(int start, int duration) {
		this.start = start;
		this.duration = duration;
	}
}


class Assignment {
	int room;
	int endTime;

	public Assignment(int room, int endTime) {
		this.room = room;
		this.endTime = endTime;
	}
}

class Solution {
	
	public int getBusiestRooom(int N, List<Appointment> appointments) {
		Queue<Integer> rooms = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			rooms.offer(i);
		}


		int index = 0;
		int time = appointments.get(0).start;
		Map<Integer, Integer> countMap = new HashMap<>();
		Queue<Assignment> queue = new PriorityQueue<>((a,b) -> a.endTime - b.endTime);
		while (true) {
			if (index == appointments.size()) {
				break;
			}

			Appointment appointment = appointments.get(index);
			if (time >= appointment.start && !rooms.isEmpty()) {
				int room = rooms.poll();
				// System.out.println("Assign: " + room);
				countMap.put(room, countMap.getOrDefault(room, 0) + 1);
				queue.offer(new Assignment(room, time + appointment.duration));
				index++;
			}

			while (queue.peek() != null && time == queue.peek().endTime) {
				Assignment assignment = queue.poll();
				// System.out.println("DeAssign: " + assignment.room);
				// time = assignment.endTime;
				rooms.offer(assignment.room);
			}

			if (rooms.isEmpty()) {
				time = queue.peek().endTime;
			} else if (index <  appointments.size()) {
				time = appointments.get(index).start;
			}
		}

		List<Integer> sortedRooms = new ArrayList<>(countMap.keySet());
		Collections.sort(sortedRooms, (a,b) -> countMap.get(a)-countMap.get(b));
		return sortedRooms.get(sortedRooms.size() - 1);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		List<Appointment> appointments = Arrays.asList(
			new Appointment(1, 5),
			new Appointment(1, 7),
			new Appointment(3, 1),
			new Appointment(4, 7)
		);
		assert sol.getBusiestRooom(2, appointments) == 0;

		appointments = Arrays.asList(
			new Appointment(1, 2),
			new Appointment(1, 2),
			new Appointment(1, 2),
			new Appointment(4, 1),
			new Appointment(4, 3),
			new Appointment(4, 5),
			new Appointment(5, 2),
			new Appointment(8, 9),
			new Appointment(8, 9)
		);
		assert sol.getBusiestRooom(2, appointments) == 0;
	}
}
