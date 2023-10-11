import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Pair<K,V> {
    public K key;
    public V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class Logger {

    private Queue<Pair<String, Integer>> queue;
    private Set<String> cache;
    
    public Logger() {
        this.queue = new LinkedList<>();
        this.cache = new HashSet<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        this.cleanUp(timestamp);
        if (this.cache.contains(message)) {
            return false;
        }
        this.cache.add(message);
        this.queue.add(new Pair<>(message, timestamp));
        return true;
    }

    private void cleanUp(int timestamp) {
        while (this.queue.size() > 0) {
            Pair<String, Integer> head = this.queue.peek();
            if (timestamp - head.value >= 10) {
                this.queue.remove();
                this.cache.remove(head.key);
            } else {
                break;
            }
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        Logger logger = new Logger();
        assert logger.shouldPrintMessage(1, "foo") == true; 
        assert logger.shouldPrintMessage(2, "bar") == true;  
        assert logger.shouldPrintMessage(3, "foo") == false;  
        assert logger.shouldPrintMessage(8, "bar") == false;  
        assert logger.shouldPrintMessage(10, "foo") == false; 
        assert logger.shouldPrintMessage(11, "foo") == true; 
    }
}