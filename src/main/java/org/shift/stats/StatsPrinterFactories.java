package org.shift.stats;

import org.shift.ValueContainer;
import org.shift.stats.mappers.FloatStatsMapper;
import org.shift.stats.mappers.IntegerStatsMapper;
import org.shift.stats.mappers.StringStatsMapper;
import org.shift.stats.models.FloatStats;
import org.shift.stats.models.IntegerStats;
import org.shift.stats.models.StringStats;

import java.math.BigInteger;
import java.math.RoundingMode;

public class StatsPrinterFactories {
    public static StatsPrinter<String> createStringPrinter(ValueContainer<String> container) {
        return new StatsPrinter<>(
                container,
                list -> {
                    var sb = new StringBuilder();
                    var mapper = new StringStatsMapper();
                    StringStats stats = mapper.apply(list);
                    sb.append("Longest: ").append(stats.longest());
                    sb.append(System.lineSeparator());
                    sb.append("Shortest: ").append(stats.shortest());
                    sb.append(System.lineSeparator());
                    return sb.toString();
                }
        );
    }

    public static StatsPrinter<BigInteger> createIntPrinter(ValueContainer<BigInteger> container) {
        return new StatsPrinter<>(
                container,
                list -> {
                    var sb = new StringBuilder();
                    var mapper = new IntegerStatsMapper();
                    IntegerStats stats = mapper.apply(list);
                    sb.append("Max: ").append(stats.max());
                    sb.append(System.lineSeparator());
                    sb.append("Min: ").append(stats.min());
                    sb.append(System.lineSeparator());
                    sb.append("Sum: ").append(stats.sum());
                    sb.append(System.lineSeparator());
                    sb.append("Average: ").append(stats.average().setScale(3, RoundingMode.FLOOR));
                    return sb.toString();
                }
        );
    }

    public static StatsPrinter<Double> createDoublePrinter(ValueContainer<Double> container) {
        return new StatsPrinter<>(
                container,
                list -> {
                    var sb = new StringBuilder();
                    var mapper = new FloatStatsMapper();
                    String format = "%.3f";
                    FloatStats stats = mapper.apply(list);
                    sb.append("Max: ").append(String.format(format, stats.max()));
                    sb.append(System.lineSeparator());
                    sb.append("Min: ").append(String.format(format, stats.max()));
                    sb.append(System.lineSeparator());
                    sb.append("Sum: ").append(String.format(format, stats.sum()));
                    sb.append(System.lineSeparator());
                    sb.append("Average: ").append(String.format(format, stats.average()));
                    return sb.toString();
                }
        );
    }
}
