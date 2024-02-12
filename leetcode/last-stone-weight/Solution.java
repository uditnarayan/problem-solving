// https://leetcode.com/problems/last-stone-weight/

import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {

        Queue<Integer> heap = new PriorityQueue<>( (a,b) -> b - a);
        for (int stone: stones) {
            heap.offer(stone);
        }

        while(heap.size() > 1) {
            int x = heap.poll();
            int y = heap.poll();
            if (x != y) {
                heap.offer(Math.abs(y-x));
            }
        }
        return heap.isEmpty() ? 0 : heap.poll();
    }
}