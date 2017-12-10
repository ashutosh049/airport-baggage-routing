package io.github.rajasharan;

import io.github.rajasharan.model.Bag;
import io.github.rajasharan.utils.InputReaderUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raja on 12/9/17.
 */
public class Application {

    public static void main(String[] args) {
        List<String> lines = InputReaderUtils.readInputFile("input.txt");

        List<Bag> bags = InputReaderUtils.processLines(lines);

        List<String> output =
                bags.stream()
                        .map(InputReaderUtils::processBag)
                        .collect(Collectors.toList());

        output.stream().forEach(System.out::println);
    }
}
