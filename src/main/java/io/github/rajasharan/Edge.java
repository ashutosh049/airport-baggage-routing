package io.github.rajasharan;

/**
 * Created by raja on 12/9/17.
 */
public class Edge {
    private String from;
    private String to;
    private int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + to + " <- " + from + ", " + weight + ")";
    }
}
