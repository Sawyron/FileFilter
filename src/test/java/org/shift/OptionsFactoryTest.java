package org.shift;

import org.apache.commons.cli.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class OptionsFactoryTest {
    private CommandLineParser parser;
    private OptionsFactory optionsFactory;

    @BeforeEach
    void setUp() {
        parser = new DefaultParser();
        optionsFactory = new OptionsFactory();
    }

    @Test
    void whenAllArgsArePassed_thenGetTheirValuesInOptions() {
        Options options = optionsFactory.provideOptions();
        String command = "-p pre -a -s -f in1.txt in2.txt";
        try {
            CommandLine line = parser.parse(options, command.split(" "));
            String prefix = line.getOptionValue("prefix");
            assertEquals("pre", prefix);
            assertTrue(line.hasOption("append"));
            assertTrue(line.hasOption("simple"));
            assertTrue(line.hasOption("full"));
            String[] args = line.getArgs();
            assertEquals(2, args.length);
            assertEquals("in1.txt", args[0]);
            assertEquals("in2.txt", args[1]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenOutputOptionsIsPassed_thenParseItAsPath() {
        Options options = optionsFactory.provideOptions();
        String command = "-o ./res";
        try {
            CommandLine line = parser.parse(options, command.split(" "));
            Path output = line.getParsedOptionValue("o");
            assertTrue(output.endsWith("res"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}