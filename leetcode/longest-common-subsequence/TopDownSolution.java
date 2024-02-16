// https://leetcode.com/problems/longest-common-subsequence/
class TopDownSolution {
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

        // do not consider char at i in text1
        int size1 = longestCommonSubsequence(text1, text2, i+1, j, memo);

        // consider char at i in text1 if matches with some char in text2 at or after j
        int size2 = 0;
        int idx = text2.indexOf(text1.charAt(i), j);
        if (idx != -1) {
            size2 = 1 + longestCommonSubsequence(text1, text2, i+1, idx+1, memo);
        }
        memo[i][j] = Math.max(size1, size2);
        return memo[i][j];
    }
}