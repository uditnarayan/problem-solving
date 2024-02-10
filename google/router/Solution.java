/**

1. Integer values
2. precision points
3. bidirectional/undirected communication
4. Two routers are at same point in network

**/

class Router {
    int x;
    int y;
    String name;

    public Router(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y =y;
    }

    public int distance(Router router) {}
}

class Solution {

    private Map<String, List<Router>> graph;
    private int range;

    public void init(List<Router> routers, int range) {
        this.range = range;
        this.graph = new HashMap<>();
        for (Router r: routers) {
            for (Router j: routers) {
                // same router, skip
                if (r.name.equals(j))
                    continue;

                // check for range and add to list
                if (r.distance(j) <= range)
                    graph.computeIfAbsent(r, val -> new ArrayList<>()).add(j);
            }
        }
        return graph;
    }


    private boolean canReach(String source, String dest) {
        if (source.equals(dest))  
            return true;

        List<Router> neighbours = this.graph.get(source);
        if (neighbours == null) {
            return false;
        }

        for (Router neighbour: neighbours) {
            if (this.canReach(neighbour.name, dest)) {
                return true;
            }
        }
        return false;
    }
    

    public boolean canReach(String source, String dest, List<Router> routers, int range) {
        this.init(routers, range);
        return this.canReach(String source, String dest);
    }
}