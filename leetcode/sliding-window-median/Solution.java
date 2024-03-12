// https://leetcode.com/problems/sliding-window-median/

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> low = new PriorityQueue<>();
        PriorityQueue<Integer> high = new PriorityQueue<>(Collections.reverseOrder());
        List<Double> medians = new ArrayList<>();

        int i = 0;
        while (i < k) {
            low.offer(nums[i]);
        }
        for (int j = 0; j < k / 2; j++) {
            high.offer(low.poll());
        }

        int balance = 0;
        while (true) {
            if (k % 2 != 0) {
                medians.add((double) low.peek());
            } else {
                medians.add((double) ((low.peek() + high.peek()) * 0.5));
            }

            if (i >= nums.length) { break; }
            int outNum = nums[i - k];
            int inNum = nums[i];

            if (outNum <= low.peek()) {
                balance -= 1;
            } else {
                balance += 1;
            }
            map.put(outNum, map.getOrDefault(outNum, 0) + 1);

            if (!low.isEmpty() && inNum <= low.peek()) {
                balance += 1;
                low.offer(inNum);
            } else {
                balance -= 1;
                high.offer(inNum);
            }

            if (balance < 0) {
                low.offer(high.poll());
            } else {
                high.offer(low.poll());
            }

            while (!low.isEmpty() && map.getOrDefault(low.peek()) > 0) {
                map.put(outNum, map.get(outNum, 0) - 1);
                low.poll();
            }
            while (!high.isEmpty() && map.getOrDefault(high.peek()) > 0) {
                map.put(outNum, map.get(outNum, 0) - 1);
                high.poll();
            }
        } 
    }
}