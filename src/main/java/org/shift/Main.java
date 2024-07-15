package org.shift;

import org.apache.commons.cli.*;
import org.shift.services.FileService;
import org.shift.services.StatsService;
import org.shift.stats.StatsPrinterFactories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        var app = new OptionsFactory();
        Options options = app.provideOptions();
        var formatter = new HelpFormatter();
        formatter.printHelp("filter", options);
        ValueContainer<Double> floatValueContainer = ContainerFactories.getDoubleContainer();
        ValueContainer<BigInteger> integerValueContainer = ContainerFactories.getIntegerContainer();
        ValueContainer<String> stringValueContainer = ContainerFactories.getStringContainer();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (!line.hasOption("s") ^ line.hasOption("f")) {
                throw new IllegalStateException("'s' and 'f' options could not present at the same time");
            }
            var sorter = new ValueSorter(List.of(integerValueContainer, floatValueContainer, stringValueContainer));
            List<Path> files = line.getArgList().stream()
                    .map(Path::of)
                    .toList();
            for (Path file : files) {
                try (var in = new FileInputStream(file.toFile())) {
                    sorter.sort(in);
                }
            }
            var statsService = new StatsService();
            statsService.addPrinter("Integer", StatsPrinterFactories.createIntPrinter(integerValueContainer));
            statsService.addPrinter("Float", StatsPrinterFactories.createDoublePrinter(floatValueContainer));
            statsService.addPrinter("String", StatsPrinterFactories.createStringPrinter(stringValueContainer));
            statsService.printStats(line.hasOption("f"));
            Path outputFolder = Objects.requireNonNullElse(line.getParsedOptionValue("o"), Paths.get(""));
            Files.createDirectories(outputFolder);
            var fileService = new FileService(
                    line.getOptionValue("p"),
                    outputFolder,
                    line.hasOption("a")
            );
            fileService.addWriter("integers.txt", new ValueWriter<>(integerValueContainer));
            fileService.addWriter("floats.txt", new ValueWriter<>(floatValueContainer));
            fileService.addWriter("strings.txt", new ValueWriter<>(stringValueContainer));
            fileService.write();
        } catch (Exception e) {
            handleExpedition(e);
        }
    }

    private static void handleExpedition(Exception e) {
        switch (e) {
            case FileNotFoundException fnf -> System.out.println("Could not find file " + fnf.getMessage());
            case IOException io -> System.out.println(io.getMessage());
            case Exception ex -> System.out.println("An exception occurred: " + ex.getMessage());
        }
    }
}
