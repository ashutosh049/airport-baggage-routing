package io.github.rajasharan;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raja on 12/10/17.
 */
public class Flights {
    private Map<String, Flight> flightMap;

    public Flights() {
        this.flightMap = new HashMap<>();
    }

    public void add(String flightId, String flightGate, String destinationCode, String departureTime) {
        flightMap.put(flightId, new Flight(flightGate, destinationCode, LocalTime.parse(departureTime)));
    }

    public String getFlightGate(String flightId) {
        return flightMap.get(flightId).getFlightGate();
    }

    public Map<String, Flight> getAllFlights() {
        return flightMap;
    }

    private static class Flight {
        private String flightGate;
        private String destinationCode;
        private LocalTime departureTime;

        public Flight(String flightGate, String destinationCode, LocalTime departureTime) {
            this.flightGate = flightGate;
            this.destinationCode = destinationCode;
            this.departureTime = departureTime;
        }

        public String getFlightGate() {
            return flightGate;
        }

        public String getDestinationCode() {
            return destinationCode;
        }

        public LocalTime getDepartureTime() {
            return departureTime;
        }

        @Override
        public String toString() {
            return "Flight(" +
                    flightGate + ',' +
                    destinationCode + ',' +
                    departureTime +
                    ')';
        }
    }
}
