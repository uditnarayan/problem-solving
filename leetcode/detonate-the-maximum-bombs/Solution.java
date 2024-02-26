import java.util.*;

class Solution {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = bombs.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i!=j && inRange(bombs[i], bombs[j])) {
                    graph.computeIfAbsent(i, val -> new ArrayList<>()).add(j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, detonateBomb(i, graph));
        }
        return answer;
    }

    private boolean inRange(int[] bomb1, int[] bomb2) {
        int x = bomb1[0] - bomb2[0];
        int y = bomb1[1] - bomb2[1];
        long distance = x*x + y*y;
        long radius = bomb1[2] * bomb1[2];
        return radius >= distance;
    }

    private int detonateBomb(int i, Map<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited.add(node);
            for (int n: graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(n)) {
                    queue.offer(n);
                }
            }
        }
        return visited.size();
    }
}