import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

interface HitCounter {
    
    public void hit(int timestamp);
    
    public int getHits(int timestamp);
}

/**
 * Queue based hit counter which return hits in last 300 seconds;
 */
class SimpleHitCounter implements HitCounter {

    private Queue<Integer> queue;
    
    public SimpleHitCounter() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void hit(int timestamp) {
        this.queue.add(timestamp);
    }
    
    @Override
    public int getHits(int timestamp) {
        while(!this.queue.isEmpty()) {
            int diff = timestamp - this.queue.peek();
            if (diff >= 300) 
                this.queue.remove();
            else 
                break;
        }
        return this.queue.size();
    }
}

/**
 * Uses deque as optimizion to improve the runtime 
 * and memory footprint in case of repitions
 */
class DequeHitCounter implements HitCounter {

    static class Pair<K,V> {
        public K key;
        public V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Deque<Pair<Integer, Integer>> queue;
    private int total;

    public DequeHitCounter() {
        this.queue = new LinkedList<>();
        this.total = 0;
    }

    @Override
    public void hit(int timestamp) {
        if (this.queue.isEmpty() || this.queue.getLast().key != timestamp) {
            this.queue.add(new Pair<>(timestamp, 1));
        } else {
            int prevValue = this.queue.getLast().value;
            this.queue.removeLast();
            this.queue.add(new Pair<>(timestamp, prevValue+1));
        }
        this.total++;
    }

    @Override
    public int getHits(int timestamp) {
        while (!this.queue.isEmpty()) {
            int diff = timestamp - this.queue.getFirst().key;
            if (diff >= 300) {
                this.total -= this.queue.getFirst().value;
                this.queue.removeFirst();
            }
            else break;
        }
        return this.total;
    }
}

public class Solution {
    public static void main(String[] args) {
        HitCounter hitCounter = new DequeHitCounter();
        hitCounter.hit(1);                      // hit at timestamp 1.
        hitCounter.hit(2);                      // hit at timestamp 2.
        hitCounter.hit(3);                      // hit at timestamp 3.
        assert hitCounter.getHits(4) == 3;      // get hits at timestamp 4, return 3.
        // System.out.println(hitCounter.getHits(4));
        hitCounter.hit(300);                    // hit at timestamp 300.
        assert hitCounter.getHits(300) == 4;    // get hits at timestamp 300, return 4.
        // System.out.println(hitCounter.getHits(300));
        assert hitCounter.getHits(301) == 3;    // get hits at timestamp 301, return 3.
        // System.out.println(hitCounter.getHits(301));
    }
}