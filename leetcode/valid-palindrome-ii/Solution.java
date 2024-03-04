// https://leetcode.com/problems/valid-palindrome-ii/

class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return checkPalindrome(s, left, right - 1) || checkPalindrome(s, left + 1, right);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean checkPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } 
            i++;
            j--;
        }
        return true;
    }
}
