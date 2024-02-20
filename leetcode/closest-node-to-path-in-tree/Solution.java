class Solution {

    public int[] closestNode(int n, int[][] edges, int[][] query) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
        	graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
        	graph.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }

        int[] answer = new int[query.length];
        int index = 0;
        for (int[] q: query) {
        	int source = q[0];
        	int target = q[1];
        	boolean[] visited = new boolean[n];
        	visited[source] = true;
        	Set<Integer> nodes = dfs(graph, source, target, visited);
            nodes.add(source);
            // System.out.println(nodes);
        	answer[index++] = findDistance(graph, n, q[2], nodes);
        }
        return answer;
    }

    public int findDistance(Map<Integer, List<Integer>> graph, int n, int source, Set<Integer> nodes) {
    	boolean[] visited = new boolean[n];
        visited[source] = true;
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(source);

    	while (!queue.isEmpty()) {	
    		int size = queue.size();
    		for (int i = 0 ; i < size; i++) {
    			int node = queue.poll();
    			if (nodes.contains(node)) {
                    // System.out.println(node);
    				return node;
    			}
    			List<Integer> neighbours = graph.getOrDefault(node, null);
    			if (neighbours != null) {
    				for (Integer ne: neighbours) {
                        if (!visited[ne]) {
                            visited[ne] = true;
    					    queue.offer(ne);
                        }
    				}
    			}
    		}
    	}
    	return -1;
    }


    public Set<Integer> dfs (Map<Integer, List<Integer>> graph, int node, int target, boolean[] visited) {
    	if (node == target) {
    		Set<Integer> set = new HashSet<>();
    		set.add(node);
    		return set;
    	}

    	List<Integer> neighbours = graph.getOrDefault(node, null);
    	if (neighbours == null) {
    		return null;
    	}

    	Set<Integer> set = null;
    	for (Integer i: neighbours) {
    		if (!visited[i]) {
	    		visited[i] = true;
	    		set = dfs(graph, i, target, visited);
	    		if (set != null) {
	    			set.add(i);
	    			return set;
	    		}
	    	}
    	}
    	return null;
    }
}