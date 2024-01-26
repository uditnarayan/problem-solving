import java.util.Arrays;

class Solution {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[nums.length];
        int j = 0;
        for(int i = 0; i < nums.length; i+=2) {
            ans[j++] = nums[i];
            ans[j++] = nums[i+1];
        }
        return ans;
    }
}