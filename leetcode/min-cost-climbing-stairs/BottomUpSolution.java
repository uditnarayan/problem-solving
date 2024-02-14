class BottomUpSolution {

	public int minCostClimbingStairs(int[] cost) {
       if (cost.length <= 1) {
       		return 0;
       }
       int[] memo = new int[cost.length + 1];
       memo[0] = 0;
       memo[1] = 0;
       for (int  i = 2; i < memo.length; i++) {
       		int downOneCost = memo[i-1] + cost[i-1];
			int downTwoCost = memo[i-2] + cost[i-2];
			memo[i] = Math.min(downOneCost, downTwoCost);
       }
       return memo[memo.length - 1];
    }

}