// https://leetcode.com/problems/happy-number


class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = sumOfSquaredDigits(n);
        while (fast != 1 && fast != slow) {
            slow = sumOfSquaredDigits(slow);
            fast = sumOfSquaredDigits(sumOfSquaredDigits(fast));
        }
        return fast == 1;
    }

    public int sumOfSquaredDigits(int number) {
        int totalSum = 0;
        while (number != 0) {
            int digit = number % 10;
            number = number / 10;
            totalSum += (Math.pow(digit, 2));
        }
        return totalSum;
    }
}
