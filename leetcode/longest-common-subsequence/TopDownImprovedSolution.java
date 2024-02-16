// https://leetcode.com/problems/longest-common-subsequence/

class TopDownImprovedSolution {
    public int longestCommonSubsequence(String text1, String text2) {
    	Integer[][] memo = new Integer[text1.length()+1][text2.length()+1];
        return longestCommonSubsequence(text1, text2, 0, 0, memo);
    }

    public int longestCommonSubsequence(String text1, String text2, int i, int j, Integer[][] memo) {
        if (i >= text1.length() || j >= text2.length()) {
            return 0;
        }

        if (memo[i][j] != null) {
        	return memo[i][j];
        }

        int size = 0;
        if (text1.charAt(i) == text2.charAt(j)) {
            size = 1 + longestCommonSubsequence(text1, text2, i+1, j+1, memo);
        } else {
            int size1 = longestCommonSubsequence(text1, text2, i+1, j, memo);
            int size2 = longestCommonSubsequence(text1, text2, i, j+1, memo);
            size = Math.max(size1, size2);
        }
        memo[i][j] = size;
        return memo[i][j];
    }
}