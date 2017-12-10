package io.github.rajasharan;

import java.util.*;

/**
 * Created by raja on 12/9/17.
 */
public class GraphBuilder {
    private Map<String, List<Edge>> graph;

    public GraphBuilder() {
        this.graph = new HashMap<>();
    }

    private void add(String from, String to, int weight) {
        List<Edge> adjacent = graph.getOrDefault(from, new ArrayList<>());
        adjacent.add(new Edge(from, to, weight));
        graph.putIfAbsent(from, adjacent);
    }

    public void addBidirectional(String node1, String node2, int weight) {
        add(node1, node2, weight);
        add(node2, node1, weight);
    }

    public Path shortestPath(String start, String end) {
        TreeSet<Path> paths = new TreeSet<>(Path.getComparator());
        paths.add(new Path(start, 0, null));
        return shortestPath(end, new HashSet<>(), paths);
    }

    private Path shortestPath(String end, Set<String> visitedNodes, TreeSet<Path> paths) {
        if (paths.stream().anyMatch(path -> path.getDestination().equals(end))) {
            return paths.stream().filter(path -> path.getDestination().equals(end)).findFirst().get();
        }
        else {
            Path path = paths.pollFirst();
            while(path != null && visitedNodes.contains(path.getDestination())) {
                path = paths.pollFirst();
            }

            if (path == null) {
                return null;
            }

            visitedNodes.add(path.getDestination());
            final int weight = path.getTotalDistance();
            final Path pathVia = path;

            List<Edge> newEdges = getGraph().get(path.getDestination());
            newEdges.stream().forEach(e -> {
                if (!visitedNodes.contains(e.getTo())) {
                    paths.add(new Path(e.getTo(), weight + e.getWeight(), pathVia));
                }
            });

            return shortestPath(end, visitedNodes, paths);
        }
    }

    Map<String, List<Edge>> getGraph() {
        return this.graph;
    }
}
