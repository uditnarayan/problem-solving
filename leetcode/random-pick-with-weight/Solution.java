// https://leetcode.com/problems/random-pick-with-weight/

class Solution {

    private int[] prefixSums;
    private int totalSum;

    public Solution(int[] w) {
        this.prefixSums = new int[w.length];
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            this.prefixSums[i] += sum;
        }
        this.totalSum = sum;
    }
    
    public int pickIndex() {
        double target = this.totalSum * Math.random();
        int low = 0;
        int high = this.prefixSums.length;
        while (low < high) {
            int mid = low + (high-low)/2;
            if (this.prefixSums[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */