package org.shift;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class Main {
    public static void main(String[] args) {
        var app = new OptionsFactory();
        Options options = app.provideOptions();
        var formatter = new HelpFormatter();
        formatter.printHelp("filter", options);
    }
}
