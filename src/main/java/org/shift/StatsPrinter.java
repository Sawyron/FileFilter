package org.shift;

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
        System.out.println(name + ":");
        if (full) {
            System.out.println(statsMapper.apply(container.values().toList()));
        }
    }
}
