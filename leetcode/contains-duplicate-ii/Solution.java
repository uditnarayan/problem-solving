import java.util.*;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 */

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        assert solution.containsNearbyDuplicate(new int[] {1,2,3,1}, 3) == true;
        assert solution.containsNearbyDuplicate(new int[] {1,0,1,1}, 1) == true;
        assert solution.containsNearbyDuplicate(new int[] {1,2,3,1,2,3}, 2) == false;    
    }
}
