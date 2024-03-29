class Data {
    public String getName() { return ""; }
    public int getValue() { return 0; }
}

class DataAPI {
    public static int[] getParallelValues(Data[] arr, int[] indices) {
        return new int[];
    }
}

class BinarySearch {

    public String search(Data[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int value = arr[mid].getValue();
            if (value == 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left].getName();
    }
}

class ParallelBinarySearch {

    // parallelization factor
    private int k;

    public ParallelBinarySearch(int k) {
        this.k = k;
    }

    public String search(Data[] arr) {

        int low = 0;
        int high = arr.length;

        while (low + k < high) {
            int[] indices = pickKIndexes(low, high);
            int[] values = DataAPI.getParallelValues(arr, indices);
            int firstZeroIndex = getFirstZeroIndex(values);
            int lastOneIndex = firstZeroIndex == -1 ? indices.length - 1 : firstZeroIndex - 1;

            if (lastOneIndex == -1) {
                right = indices[0];
            } else if (firstZeroIndex == -1) {
                left = indices[indices.length-1] + 1;
            } else {
                left  = indices[lastOneIndex];
                right  = indices[firstZeroIndex];
            }
        }

        int[] indices = IntStream.range(left,right).toArray();
        int[] values = DataAPI.getParallelValues(arr, indices);
        int firstZeroIndex = getFirstZeroIndex(values);
        return arr[indices[firstZeroIndex]].getName();
    }

    private int[] pickKIndexes(int low, int high) {
        int indices[] = new int[k];
        for (int i = 1; i <= k && low < high; i++) {
            indices[i] = low;
            low += k;
        }
    }

    private int getFirstZeroIndex(int[] values) {}

    private int getLastOneIndex(int[] values) {}
}