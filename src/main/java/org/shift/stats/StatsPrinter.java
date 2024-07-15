package org.shift.stats;

import org.shift.ValueContainer;

import java.util.List;
import java.util.function.Function;

public class StatsPrinter<T> {
    private final ValueContainer<T> container;
    private final Function<List<T>, String> statsMapper;

    public StatsPrinter(ValueContainer<T> container, Function<List<T>, String> statsMapper) {
        this.container = container;
        this.statsMapper = statsMapper;
    }

    public void printStats(String name, boolean full) {
        System.out.println("---");
        System.out.println("Type: " + name);
        if (container.getSize() == 0) {
            System.out.println("Not found");
            return;
        }
        System.out.println("Found: " + container.tokens().count());
        if (full) {
            System.out.println(statsMapper.apply(container.values().toList()));
        }
    }
}
