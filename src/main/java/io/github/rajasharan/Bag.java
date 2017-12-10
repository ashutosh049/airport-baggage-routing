package io.github.rajasharan;

/**
 * Created by raja on 12/10/17.
 */
public class Bag {
    private String bagId;
    private String entryPoint;
    private String flightGate;

    public Bag(String bagId, String entryPoint, String flightGate) {
        this.bagId = bagId;
        this.entryPoint = entryPoint;
        this.flightGate = flightGate;
    }

    public String getBagId() {
        return bagId;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public String getFlightGate() {
        return flightGate;
    }

    @Override
    public String toString() {
        return "Bag(" +
                bagId + ',' +
                entryPoint + ',' +
                flightGate +
                ')';
    }
}
