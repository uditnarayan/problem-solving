class TopDownSolution {
    private Map<Integer, Integer> maxSum;
    private int[] nums;

    private int robFrom(int i) {
        if (i == 0) return this.nums[0];
        if (i == 1) return Math.max(this.nums[0], this.nums[1]);
        if (!maxSum.containsKey(i)) {
            maxSum.put(i, Math.max(this.robFrom(i-1), this.robFrom(i-2)+this.nums[i]));
        }
        return maxSum.get(i);
    }

    public int rob(int[] nums) {
        this.nums = nums;
        this.maxSum = new HashMap<>();
        return this.robFrom(nums.length - 1);
    }
}