// https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/

class RecursiveSolution {
    public int maximumScore(int[] nums, int[] multipliers) {
        return maximumScore(nums, multipliers, 0, nums.length - 1, 0);
    }

    public int maximumScore(int[] nums, int[] multipliers, int start, int end, int opIndex) {
        if (opIndex == multipliers.length) {
            return 0;
        }

        if (start == end) {
            return nums[start] * multipliers[opIndex];
        }

        int scoreStart = (nums[start] * multipliers[opIndex]) + maximumScore(nums, multipliers, start + 1, end, opIndex + 1);
        int scoreEnd = (nums[end] * multipliers[opIndex]) + maximumScore(nums, multipliers, start, end - 1, opIndex + 1);
        return Math.max(scoreStart, scoreEnd);
    }
}