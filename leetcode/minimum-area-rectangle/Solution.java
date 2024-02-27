// https://leetcode.com/problems/minimum-area-rectangle

class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point: points) {
        	map.computeIfAbsent(point[0], val -> new HashSet<>()).add(point[1]);
        }

        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
        	for (int j = i + 1; j < points.length; j++) {
        		int area = getArea(points, i, j, map);
        		if (area != -1) {
        			minArea = Math.min(minArea, area);
        		}
        	}
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }

    public int getArea(int[][] points, int i, int j, Map<Integer, Set<Integer>> map) {
    	int x1 = points[i][0], y1 = points[i][1]; // A
        int x2 = points[j][0], y2 = points[j][1]; // B
        if (x1 == x2 || y1 == y2) {
        	return -1;
        } 

        if (map.get(x1).contains(y2) && map.get(x2).contains(y1))  {
        	return Math.abs(x1-x2) *  Math.abs(y1-y2);
        } 
        return -1;
    }
}