// https://leetcode.com/problems/ipo/

import java.util.*;

class Project implements Comparable<Project> {
    int capital;
    int profit;

    public Project(int capital, int profit) {
        this.capital = capital;
        this.profit = profit;
    }

    public int compareTo(Project project) {
        return capital - project.capital;
    }
}

class Solution {
    
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        PriorityQueue<Project> projects = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            projects.offer(new Project(capital[i], profits[i]));
        }

        PriorityQueue<Project> maxProfit = new PriorityQueue<Project>(n, (a,b) -> b.profit - a.profit);
        int j = 0;
        for (int i = 0; i < k; i++) {
            while (!projects.isEmpty() && projects.peek().capital <= w) {
                maxProfit.offer(projects.poll());
            }
            if (maxProfit.isEmpty()) {
                break;
            }
            w += maxProfit.poll().profit;
        }
        return w;
    }
}