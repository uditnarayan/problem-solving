// https://leetcode.com/problems/paths-in-maze-that-lead-to-same-room/


class Solution {
    public int numberOfPaths(int n, int[][] corridors) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++)
            graph.put(i, new HashSet<>());

        for (int[] c : corridors) {
            if (c[0] < c[1]) {
                graph.get(c[0]).add(c[1]);
            } else {
                graph.get(c[1]).add(c[0]);
            }
        }

        int score = 0;
        for (int[] c: corridors) {
            Set<Integer> set0 = graph.get(c[0]);
            Set<Integer> set1 = graph.get(c[1]);
            for (int k: set0) {
                if (set1.contains(k)) score++;
            }
        }
        return score;
    }
}
