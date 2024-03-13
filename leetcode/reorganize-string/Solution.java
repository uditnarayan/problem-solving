// https://leetcode.com/problems/reorganize-string/

class Solution {
    static class Node {
        char c;
        int freq;

        public Node(char c, int freq) {
            this.c =c;
            this.freq = freq;
        }
    }

    public String reorganizeString(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c: s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c,0)+1);
        }

        Queue<Node> heap = new PriorityQueue<>((a,b) -> b.freq - a.freq);
        for (Map.Entry<Character, Integer> entry: counts.entrySet()) {
            heap.offer(new Node(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            Node first = heap.poll();
            if (sb.length() == 0 || first.c != sb.charAt(sb.length() - 1)) {
                sb.append(first.c);
                --first.freq;
                if (first.freq > 0) {
                    heap.offer(first);
                }
            } else {
                if (heap.isEmpty()) {
                    return "";
                }
                Node second = heap.poll();
                sb.append(second.c);
                --second.freq;
                if (second.freq > 0) {
                    heap.offer(second);
                }
                heap.offer(first);
            }
        }
        return sb.toString();
    }
}