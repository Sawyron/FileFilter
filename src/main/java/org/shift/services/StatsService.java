package org.shift.services;

import org.shift.stats.StatsPrinter;

import java.util.ArrayList;
import java.util.List;

public class StatsService {
    private record PrinterEntry(StatsPrinter<?> printer, String name) {
    }

    private final List<PrinterEntry> entries = new ArrayList<>();

    public void printStats(boolean full) {
        for (PrinterEntry entry : entries) {
            entry.printer.printStats(entry.name, full);
        }
    }

    public void addPrinter(String name, StatsPrinter<?> printer) {
        entries.add(new PrinterEntry(printer, name));
    }
}
