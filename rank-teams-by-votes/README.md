# Rank Teams by Votes

https://leetcode.com/problems/rank-teams-by-votes


**How do we resolve the conflicts or ties?** 

If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.

**How do I store or represent data in an efficient manner?**

Build a rank data struture which can either be HashMap or a 2D array. 

**Analysis**

Assuming 26 characters in input charset to respresnt teams.

Time Complexity:    O(26n+(26^2 * log26))

Space Complexity:   O(26n+(26^2 * log26))
