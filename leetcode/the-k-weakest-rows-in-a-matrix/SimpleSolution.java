// https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/

class SimpleSolution {
    class Pair {
		int row;
		int count;		

		Pair(int row, int count) {
			this.row = row;
			this.count = count;
		}
	}


	public int[] kWeakestRows(int[][] mat, int k) {
		int n = mat.length;
		int m = mat[0].length;
        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
        	int s = Arrays.stream(mat[i]).sum();
        	pairs[i] = new Pair(i, s);
        }

        Arrays.sort(pairs, (a,b) -> {
        	return a.count == b.count ? a.row - b.row : a.count -  b.count;
        });

        int[] indexes = new int[k];
        for (int i = 0; i < k; i++) {
        	indexes[i] = pairs[i].row;
        }
        return indexes;
    }
}