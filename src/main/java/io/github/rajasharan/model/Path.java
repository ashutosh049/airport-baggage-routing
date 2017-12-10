package io.github.rajasharan.model;

import java.util.Comparator;

/**
 * Created by raja on 12/9/17.
 */
public class Path {
    private String destination;
    private Path pathVia;
    private int totalDistance;

    public Path(String destination, int totalDistance, Path pathVia) {
        this.destination = destination;
        this.totalDistance = totalDistance;
        this.pathVia = pathVia;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    @Override
    public String toString() {
        if (pathVia != null) {
            return pathVia.toString() + " " + destination;
        } else {
            return destination;
        }
    }

    public static Comparator<Path> getComparator() {
        return (p1, p2) -> p1.getTotalDistance() < p2.getTotalDistance()? -1: 1;
    }
}
