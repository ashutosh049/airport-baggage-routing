package io.github.rajasharan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raja on 12/9/17.
 */
public class Application {
    private static CurrentReadState currentReadState;
    private static GraphBuilder graphBuilder;
    private static Flights flights;
    private static List<Bag> bags;

    public static void main(String[] args) {
        List<String> lines = readFile("input.txt");

        initialize();
        processLines(lines);

        bags.stream().forEach(Application::processAndPrintBagInfo);
    }

    private static List<String> readFile(String filename) {
        try {
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            List<String> lines = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    private static void initialize() {
        currentReadState = CurrentReadState.NOT_STARTED;
        graphBuilder = new GraphBuilder();
        flights = new Flights();
        bags = new ArrayList<>();
    }

    private static void processAndPrintBagInfo(Bag bag) {
        String bagId = bag.getBagId();
        String entryPoint = bag.getEntryPoint();
        String flightGate = bag.getFlightGate();

        Path path = graphBuilder.shortestPath(entryPoint, flightGate);
        System.out.print(bagId + " ");
        System.out.println(path + " : " + path.getTotalDistance());
    }

    private static void processLines(List<String> lines) {
        for (String line: lines) {
            if (line.startsWith("#")) {
                moveReadState();
            }
            else if (currentReadState == CurrentReadState.CONVEYOR) {
                readConveyorSystem(line);
            }
            else if (currentReadState == CurrentReadState.DEPARTURES) {
                readDepartures(line);
            }
            else if (currentReadState == CurrentReadState.BAGS) {
                readBags(line);
            }
        }
    }

    private static void moveReadState() {
        if (currentReadState == CurrentReadState.NOT_STARTED) {
            currentReadState = CurrentReadState.CONVEYOR;
        } else if (currentReadState == CurrentReadState.CONVEYOR) {
            currentReadState = CurrentReadState.DEPARTURES;
        } else if (currentReadState == CurrentReadState.DEPARTURES) {
            currentReadState = CurrentReadState.BAGS;
        }
    }

    private static void readConveyorSystem(String line) {
        String [] input = splitString(line);
        graphBuilder.addBidirectional(input[0], input[1], Integer.parseInt(input[2]));
    }

    private static void readDepartures(String line) {
        String [] input = splitString(line);
        flights.add(input[0], input[1], input[2], input[3]);
    }

    private static void readBags(String line) {
        String [] input = splitString(line);
        String flightGate = "ARRIVAL".contains(input[2])? "BaggageClaim": flights.getFlightGate(input[2]);
        bags.add(new Bag(input[0], input[1], flightGate));
    }

    private static String[] splitString(String string) {
        return string.split("\\s+");
    }

    private enum CurrentReadState {
        NOT_STARTED,
        CONVEYOR,
        DEPARTURES,
        BAGS
    }

    private static void debug() {
        System.out.println(graphBuilder.getGraph());
        System.out.println(flights.getAllFlights());
        System.out.println(bags);
    }
}
