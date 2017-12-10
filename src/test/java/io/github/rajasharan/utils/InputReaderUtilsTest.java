package io.github.rajasharan.utils;

import io.github.rajasharan.model.Bag;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by raja on 12/10/17.
 */
public class InputReaderUtilsTest {

    @Test
    public void testFileLoads(){
        InputReaderUtils.readInputFile("testInput.txt");
    }

    @Test(expected = Exception.class)
    public void testFileDoesntLoad() {
        InputReaderUtils.readInputFile("nofile.txt");
    }

    @Test
    public void testProcessBagEx1() {
        List<String> lines = InputReaderUtils.readInputFile("testInput.txt");

        List<Bag> bags = InputReaderUtils.processLines(lines);
        Assertions.assertThat(bags).hasSize(1);

        String output = InputReaderUtils.processBag(bags.get(0));
        Assertions.assertThat(output).contains("B1 S B A : 5");
    }

    @Test
    public void testProcessBagEx2() {
        List<String> inputlines = Arrays.asList(
                "#",
                "S    A      7",
                "A    B      3",
                "B    S      2",

                "#",
                "UA1  A   LAX    09:30",

                "#",
                "Bag1   S  UA1"
        );

        List<Bag> bags = InputReaderUtils.processLines(inputlines);
        Assertions.assertThat(bags).hasSize(1);

        String output = InputReaderUtils.processBag(bags.get(0));
        Assertions.assertThat(output).contains("Bag1 S B A : 5");
    }

    @Test
    public void testArrivalBags() {
        List<String> inputlines = Arrays.asList(
                "# graph",
                "S    A      7",
                "A    B      3",
                "B    S      2",
                "BaggageClaim A 1",

                "# flights",
                "UA1 A  LAX    09:30",

                "# bags",
                "Bag1   S  UA1",
                "Bag2   S  ARRIVAL"
        );

        List<Bag> bags = InputReaderUtils.processLines(inputlines);
        Assertions.assertThat(bags).hasSize(2);

        String baggageClaimBag = InputReaderUtils.processBag(bags.get(1));
        Assertions.assertThat(baggageClaimBag).contains("Bag2 S B A BaggageClaim : 6");
    }

    @Test
    public void testProcessLines() {
        List<String> lines = InputReaderUtils.readInputFile("testInput.txt");
        List<Bag> bags = InputReaderUtils.processLines(lines);

        Assertions.assertThat(lines).hasSize(8);
        Assertions.assertThat(bags).hasSize(1);
    }
}