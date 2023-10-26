/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock
 */

public class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(prices[i], minPrice);
            profit = (prices[i] - minPrice) > profit ? (prices[i] - minPrice) : profit;
        }
        return profit;
    }
}
