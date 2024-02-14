class TopDownSolution {
    private Map<Integer, Integer> memo;
	private int[] cost;

	public int minCostToReachFloor(int i) {
		if (i <= 1) 
			return 0;

		if (!memo.containsKey(i)) {
			int downOneCost = this.minCostToReachFloor(i-1) + cost[i-1];
			int downTwoCost = this.minCostToReachFloor(i-2) + cost[i-2];
			memo.put(i, Math.min(downOneCost, downTwoCost));
		}
		return memo.get(i);
	}
	
	public int minCostClimbingStairs(int[] cost) {
        this.memo = new HashMap<>();
        this.cost = cost;
        return this.minCostToReachFloor(cost.length);
    } 
}