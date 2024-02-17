// https://leetcode.com/problems/decode-string

import java.util.*;

class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) != ']') {
        		stack.push(s.charAt(i));
        		continue;
        	}

        	List<Character> chars = new ArrayList<>();
        	while (stack.peek() != '[') {
        		chars.add(stack.pop());
        	}
        	stack.pop(); // pop `[`
 			int k = 0;
            int base = 1; // This is important, as numbers ending with 0 will cause problem
 			while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
 				int d = stack.pop() - '0';
                k = k + d * base;  
 				base *= 10;
 			}
 			while(k > 0) {
 				for (int j = chars.size() - 1; j >= 0; j--) {
 					stack.push(chars.get(j));
 				}
 				k--;
 			}
        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.decodeString("3[a]2[bc]"));
        System.out.println(s.decodeString("10[leetcode]"));
    }
}