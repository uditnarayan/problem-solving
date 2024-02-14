class Pair {
	int x;
	int y;

	public Pair (int x, in y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {

	private Pair[] directions = new Pair[]{
		new Pair(-1, 0);
		new Pair(0, 0);
		new Pair(0, -1);
		new Pair(-1, -1);
	}

	private isValidLand(int[][] land, int x, int y) {
		int m = land.length;
		int n = land[0].length;
		return x >= 0 && x < m && y >= 0 && y < n;
	}

	private void dfs (int[][] land, Set<Pair> visited, Pair pair) {
		if (visited.contains(pair)) {
			return;
		}
		visited.add(pair);
		for (Pair dir: directions) {
			if (isValidLand(land, pair.x + dir.x, pair.y + dir.y)) {
				dfs(land, visited, new Pair(pair.x + dir.x, pair.y + dir.y));
			}
		}
	}


	public Pair findWaterTower(int[][] land, Pair town1, Pair town2) {

		Set<Pair> visited1 = new HashSet<>();
		dfs(land, visited1, town1);
		Set<Pair> visited2 = new HashSet<>();
		dfs(land, visited2, town2);

		visited1.retainAll(visited2);
		return Collections.max(visited1);
	}

}