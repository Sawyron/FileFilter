package org.shift;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.nio.file.Path;

public class OptionsFactory {
    public Options provideOptions() {
        var options = new Options();
        Option prefix = Option.builder("p")
                .longOpt("prefix")
                .argName("prefix")
                .hasArg()
                .build();
        Option output = Option.builder("o")
                .longOpt("output")
                .argName("output")
                .hasArg()
                .type(Path.class)
                .build();
        Option append = Option.builder("a")
                .longOpt("append")
                .build();
        Option simpleStats = Option.builder("s")
                .longOpt("simple")
                .build();
        Option fullStats = Option.builder("f")
                .longOpt("full")
                .build();
        Option help = Option.builder("h")
                .longOpt("help")
                .build();
        options.addOption(prefix);
        options.addOption(output);
        options.addOption(append);
        options.addOption(simpleStats);
        options.addOption(fullStats);
        options.addOption(help);
        return options;
    }
}
