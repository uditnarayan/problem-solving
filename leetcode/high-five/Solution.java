import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

class Solution {
    public int[][] highFive(int[][] items) {
        int maxSize = 5;
        Map<Integer, Queue<Integer>> allScores = new TreeMap<>();
        for (int[] item: items) {
            int id = item[0];
            int score = item[1];
            if (!allScores.containsKey(id)) {
                allScores.put(id, new PriorityQueue<>());
            }
            Queue<Integer> heap = allScores.get(id);
            heap.offer(score);
            if (heap.size() > maxSize) {
                heap.poll();
            }
        }   

        List<int[]> solution = new ArrayList<>();
        for (Map.Entry<Integer, Queue<Integer>> entry: allScores.entrySet()) {
            int sum = entry.getValue().stream().mapToInt(Integer::intValue).sum();
            solution.add(new int[]{ entry.getKey(), sum/maxSize });
        }
        int[][] solutionArray = new int[solution.size()][];
        return solution.toArray(solutionArray);
    }
}