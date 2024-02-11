/**

1. Integer values
2. precision points
3. bidirectional/undirected communication
4. Two routers are at same point in network

**/

import java.util.*;

class Router {
    int x;
    int y;
    int range;
    String name;

    public Router(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y =y;
        this.range = 10;
    }

    public Router(String name, int x, int y, int range) {
        this.name = name;
        this.x = x;
        this.y =y;
        this.range = range;
    }

    public boolean inRange(Router router) {
        int distance = (int) Math.hypot(Math.abs(this.x - router.x), Math.abs(this.y - router.y));
        return distance <= this.range;
    }

    public boolean equals(Router router) {
        return this.name.equals(router.name);
    }
}

class Solution {

    private Map<String, List<Router>> graph;


    public void init(List<Router> routers) {
        this.graph = new HashMap<>();
        for (Router r: routers) {
            for (Router j: routers) {
                // same router, skip
                if (r.equals(j))
                    continue;

                // check for range and add to list
                if (r.inRange(j)) {
                    graph.computeIfAbsent(r.name, val -> new ArrayList<>()).add(j);
                }
            }
        }
    }
    

    public boolean canReach(String source, String dest, List<Router> routers) {
        this.init(routers);
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            String name = stack.pop();
            visited.add(name);
            if (name.equals(dest)) {
                return true;
            }
            if (graph.containsKey(name)) {
                for(Router n: graph.get(name)) {
                    if (!visited.contains(n.name)) {
                        stack.push(n.name);
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        
        List<Router> routers = new ArrayList<>();
        routers.add(new Router("A", 0, 0, 10));
        routers.add(new Router("B", 0, 8, 10));
        routers.add(new Router("C", 0, 17, 10));
        routers.add(new Router("D", 11, 0, 10));
        
        Solution sol = new Solution();
        assert sol.canReach("A",  "A", routers) == true;
        assert sol.canReach("A",  "B", routers) == true;
        assert sol.canReach("A",  "C", routers) == true;
        assert sol.canReach("A",  "D", routers) == false;
        assert sol.canReach("B",  "D", routers) == false;
    }
}