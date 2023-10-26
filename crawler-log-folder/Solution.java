/**
 * https://leetcode.com/problems/crawler-log-folder/
 */

public class Solution {
    public int minOperations(String[] logs) {
        int count = 0;
        for (String log: logs) {
            if (log.equals("./")) {
                continue;
            } else if (log.equals("../")) {
                count = count > 0 : count - 1: count;
            } else {
                ++count;
            }
        }
    }
}
