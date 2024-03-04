// https://leetcode.com/problems/valid-palindrome-iv/

class Solution {
    public boolean makePalindrome(String s) {
        int count = 0;
        int left = 0;
        int right = s.length()-1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                count++;
                if (count > 2) {
                    return false;
                }
            }
            left++;
            right--;
        }
        return true;
    }
}
