// https://leetcode.com/problems/find-median-from-data-stream

class MedianFinder {

    private PriorityQueue<Integer> low;
    private PriorityQueue<Integer> high;

    public MedianFinder() {
        low = new PriorityQueue<>();
        high = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        low.offer(num);
        high.offer(low.poll());
        if (low.size() < high.size()) {
            low.offerr(high.poll());
        }
    }
    
    public double findMedian() {
        return low.size() > high.size() ? low.peek() : (low.peek() + high.peek()) * 0.5;
    }
}