/**
 * https://leetcode.com/problems/parallel-courses/description/
 */
 
class Solution {
    public int minimumSemesters(int n, int[][] relations) {
    	int[] indegree = new int[n+1];
 		Map<Integer, List<Integer>> graph = new HashMap<>();
    	for(int[] r: relations) {
    		int source = r[0];
    		int dest = r[1];
    		graph.computeIfAbsent(source, val -> new ArrayList<Integer>()).add(dest);
    		indegree[dest]++;
    	}

    	Queue<Integer> queue = new LinkedList<>();
    	for (int i = 0; i < indegree.length; i++) {
    		if (indegree[i] == 0) {
    			queue.offer(i);
    		}
    	}
    	int sem = 0;
    	while(!queue.isEmpty()) {
    		sem++;
    		int size = queue.size();
    		while (size > 0) {
    			int node = queue.poll();
    			if (graph.containsKey(node)) {

    				for (int r: graph.get(node)){
    					indegree[r]--;
    					if (indegree[r] == 0) {
    						queue.offer(r);
    					}
    				}
    			}
                size--;
    		}
    	}
        for (int i = 0; i < n; i++) {
    		if (indegree[i] != 0) {
    			return -1;
    		}
    	}
    	return sem;
    }
}