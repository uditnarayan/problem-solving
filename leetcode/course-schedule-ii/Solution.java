/**
 * 
 *  https://leetcode.com/problems/course-schedule-ii/description/
 * 
 */


class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

    	int[] indegree = new int[numCourses];
 		int[] order = new int[numCourses];

 		Map<Integer, List<Integer>> graph = new HashMap<>();
    	for(int[] p: prerequisites) {
    		int source = p[1];
    		int dest = p[0];
    		graph.computeIfAbsent(source, val -> new ArrayList<Integer>()).add(dest);
    		indegree[dest]++;
    	}

    	Queue<Integer> queue = new LinkedList<>();
    	for (int i = 0; i < numCourses; i++) {
    		if (indegree[i] == 0) {
    			queue.offer(i);
    		}
    	}

    	int i = 0;
    	while (!queue.isEmpty()) {
    		int node = queue.poll();
    		order[i++] = node;

    		if(graph.containsKey(node)) {
    			for(Integer n: graph.get(node)) {
    				indegree[n]--;
    				if (indegree[n] == 0) {
    					queue.offer(n);
    				}
    			}
    		}
    	}
        
        return i == numCourses ? order : new int[0];
    }
}