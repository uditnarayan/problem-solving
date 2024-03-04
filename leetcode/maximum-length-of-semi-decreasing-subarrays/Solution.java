// https://leetcode.com/problems/maximum-length-of-semi-decreasing-subarrays/

class Solution {
    public int maxSubarrayLength(int[] nums) {
        int n = nums.length;
        if (nums == null && nums.length == 0) {
        	return 0;
        }

        int[] rightMin = new int[n];
        rightMin[n-1] = nums[n-1];
        for (int i = n-2; i >=0 ; i--) {
        	rightMin[i] = Math.min(rightMin[i+1], nums[i]);
        }

        int maxLen = 0;
        for (int i = 0, j = 0; i < n && j < n; i++) {
        	j = Math.max(i, j);
        	while (j < n && rightMin[j] < nums[i]) {
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
        }
        return maxLen;
    }
}