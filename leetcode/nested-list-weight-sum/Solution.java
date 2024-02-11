// https://leetcode.com/problems/nested-list-weight-sum/

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation

interface NestedInteger {
      // Constructor initializes an empty nested list.
    public NestedInteger();

    // Constructor initializes a single integer.
    public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


class Solution {

    class NestedIntegerLevel {
        List<NestedInteger> list;
        int depth;

        NestedIntegerLevel(List<NestedInteger> list, int depth) {
            this.list = list;
            this.depth = depth;
        }
    }

    public int depthSum(List<NestedInteger> nestedList) {
        
        if (nestedList == null) {
            return 0;
        }

        int sum = 0;
        Queue<NestedIntegerLevel> queue = new LinkedList<>();
        queue.offer(new NestedIntegerLevel(nestedList, 1));
        while (!queue.isEmpty()) {
            NestedIntegerLevel nestedIntegerLevel = queue.poll();
            List<NestedInteger> list = nestedIntegerLevel.list;
            int depth = nestedIntegerLevel.depth;

            for (NestedInteger nestedInteger: list) {
                if (nestedInteger.isInteger()) {
                    sum += nestedInteger.getInteger() * depth;
                } else {
                    queue.offer(new NestedIntegerLevel(nestedInteger.getList(), depth+1));
                }
            }
        }
        return sum;
    }
}