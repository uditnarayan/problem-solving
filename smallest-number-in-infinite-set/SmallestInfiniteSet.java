import java.util.*;

class SmallestInfiniteSet {
    private Set<Integer> set;
    private Queue<Integer> heap;
    private Integer current;

    public SmallestInfiniteSet() {
        this.set = new HashSet<>();
        this.heap = new PriorityQueue<>();
    }
    
    public int popSmallest() {
        int answer;
        if (!heap.isEmpty()) {
            answer = heap.poll();
            set.remove(answer);
        } {
            answer = current;
            current++;
        }
        return answer;
    }
    
    public void addBack(int num) {
        if (current <= num || set.contains(num)) {
            return;
        }
        set.add(num);
        heap.offer(num);
    }
}
