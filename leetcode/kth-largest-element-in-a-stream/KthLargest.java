// https://leetcode.com/problems/kth-largest-element-in-a-stream/

import java.util.*;

class KthLargest {

	private Queue<Integer> minHeap;
	private int k;

    public KthLargest(int k, int[] nums) {
    	this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int n: nums) {
        	this.add(n);
        }
    }
    
    public int add(int val) {
        this.minHeap.offer(val); 
        if (this.minHeap.size() > this.k) {
        	this.minHeap.poll();
        	return this.minHeap.peek();
        }
        return -1;
    }
}