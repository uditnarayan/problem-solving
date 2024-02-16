// https://leetcode.com/problems/longest-common-subsequence/

class RecursiveSolution {
    public int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, text2, 0, 0);
    }

    public int longestCommonSubsequence(String text1, String text2, int i, int j) {
        if (i >= text1.length() || j >= text2.length()) {
            return 0;
        }

        int size = Math.max(
            longestCommonSubsequence(text1, text2, i+1, j), 
            longestCommonSubsequence(text1, text2, i, j+1)
        );
        return text1.charAt(i) == text2.charAt(j) ? size + 1: size;
    }
}