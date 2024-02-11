import java.util.*;

class Snap {
	String name;
	String sourceDisk;
	String[] childDisks;

	public Snap(String name, String sourceDisk, String[] childDisks) {
		this.name = name;
		this.sourceDisk = sourceDisk;
		this.childDisks = childDisks;
	}
}

class FollowUpSolution {

	Map<String, List<String>> graph;
	Map<String, Integer> indegree;

	public void initGraph(List<String> disks, List<Snap> snaps) {

		this.graph = new HashMap<>();
		this.indegree = new HashMap<>();

		for (String disk: disks) {
			this.graph.put(disk, null);
			this.indegree.put(disk, 0);
		}

		for(Snap snap: snaps) {
			String source = snap.sourceDisk;
			if (snap.childDisks == null)
				continue;
			for (String child: snap.childDisks) {
				this.graph.computeIfAbsent(child, val -> new ArrayList<>()).add(source);
				this.indegree.put(source, this.indegree.get(source) + 1);
			}
		}
	}
 
	public List<List<String>> getDeletionOrder(List<String> disks, List<Snap> snaps) {

		this.initGraph(disks, snaps);

		Queue<String> queue = new LinkedList<>();
		for (Map.Entry<String, Integer> entry: this.indegree.entrySet()) {
			// System.out.println(entry.getKey() + "-" + entry.getValue());
			if (entry.getValue() == 0) {
				queue.offer(entry.getKey());
			}
		}


		List<List<String>> answers = new ArrayList<>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<String> answer = new ArrayList<>();
			for (int i = 0 ; i < size; i++) {
				String disk = queue.poll();
				answer.add(disk);
				if (this.graph.get(disk) != null) {
					for (String source: this.graph.get(disk)) {
						this.indegree.put(source, this.indegree.get(source) - 1);
						if (this.indegree.get(source) == 0) {
							queue.offer(source);
						}
					}
				}
			}
			answers.add(answer);
		}
		return answers;
	}

	public static void main(String[] args) {
		FollowUpSolution solution = new FollowUpSolution();
		List<String> disks = Arrays.asList("A", "B", "C", "D", "E", "F", "N", "X");
		List<Snap> snaps = Arrays.asList(
			new Snap("alpha", "A", new String[]{"B", "D"}),
			new Snap("alpha2", "A", null),
			new Snap("beta", "B", null),
			new Snap("beta_x", "B", new String[]{"C", "E"}),
			new Snap("beta_c", "C", new String[]{"N"}),
			new Snap("beta_e", "E", new String[]{"N"}),
			new Snap("pi", "D", new String[]{"F", "E"})
		);
		List<List<String>> answer = solution.getDeletionOrder(disks, snaps);
		for (List<String> list: answer) {
			System.out.println(String.join(", ", list));
		}
	}
}

