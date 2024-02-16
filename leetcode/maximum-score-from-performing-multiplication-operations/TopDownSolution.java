https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/

class TopDownSolution {
    public int maximumScore(int[] nums, int[] multipliers) {
        Integer[][] memo = new Integer[multipliers.length+1][multipliers.length+1];
        return maximumScore(nums, multipliers, 0, 0, memo);
    }

    public int maximumScore(int[] nums, int[] multipliers, int start, int opIndex, Integer[][] memo) {
        int n = nums.length;
        if (opIndex == multipliers.length) {
            return 0;
        }

        if (memo[opIndex][start] != null) {
            return memo[opIndex][start];
        }

        int scoreStart = (nums[start] * multipliers[opIndex]) + maximumScore(nums, multipliers, start + 1, opIndex + 1, memo);
        int end = (n - 1) - (opIndex - start);
        int scoreEnd = (nums[end] * multipliers[opIndex]) + maximumScore(nums, multipliers, start, opIndex + 1, memo);
        memo[opIndex][start] = Math.max(scoreStart, scoreEnd);
        return memo[opIndex][start];
    }
}