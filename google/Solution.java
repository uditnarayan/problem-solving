package google;

import java.util.*;

public class Solution {
    private String[] operators;

    public Solution() {
        this.operators = new String[] { "+", "*" };
    }

    public String evaluate(String[] expression) {
        Stack<String> stack = new Stack<>();
        for (String ch : expression) {
            if (ch.equals("+")) {
                String a = stack.pop();
                String b = stack.pop();
                Integer result = Integer.parseInt(a) + Integer.parseInt(b);
                stack.push(result.toString());
            } else if (ch.equals("*")) {
                String a = stack.pop();
                String b = stack.pop();
                Integer result = Integer.parseInt(a) * Integer.parseInt(b);
                stack.push(result.toString());
            } else {
                stack.push(ch);
            }
        }
        return stack.pop();
    }
    
    public String threeSum(String[] digits, String target) {
        for (String op1 : this.operators) {
            for (String op2 : this.operators) {
                String[] expression1 = new String[] { digits[0], digits[1], op1,  digits[2], op2 };
                if (this.evaluate(expression1).equals(target)) {
                    return String.join(" " ,expression1);
                }

                String[] expression2 = new String[] { digits[0], digits[1], digits[2], op1, op2 };
                if (this.evaluate(expression2).equals(target)) {
                    return String.join(" " ,expression2);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = solution.threeSum(new String[]{"2", "3", "4"}, "14");
        System.out.println(expression);
    }    
}
