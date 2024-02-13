// https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/


class HeapBinSearchSolution {

	class Pair {
		int row;
		int count;		

		Pair(int row, int count) {
			this.row = row;
			this.count = count;
		}
	}
 
	private int binarySearch(int[] row) {
		int low = 0;
        int high = row.length;
        while (low < high) {
        	int mid = low + (high - low) / 2;
        	if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
	}

	public int[] kWeakestRows(int[][] mat, int k) {
		int m = mat.length;
        int n = mat[0].length;

        Queue<Pair> heap = new PriorityQueue<>((a,b) -> {
        	return a.count == b.count ? b.row - a.row : b.count - a.count;
        });

        for (int i = 0; i < m; i++) {
        	int s = this.binarySearch(mat[i]);
        	heap.offer(new Pair(i, s));
        	if (heap.size() > k) {
        		heap.poll();
        	}
        }

        int[] indexes = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            Pair pair = heap.poll();
            indexes[i] = pair.row;
        }

        return indexes;
	}
}