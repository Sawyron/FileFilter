package org.shift;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Comparator;

public class StatsPrinterFactories {
    public StatsPrinter<String> createStringPrinter(ValueContainer<String> container) {
        return new StatsPrinter<>(
                container,
                list -> {
                    var sb = new StringBuilder();
                    long count = list.size();
                    String max = list.stream()
                            .max(Comparator.comparing(String::length))
                            .orElse("");
                    String min = list.stream()
                            .min(Comparator.comparing(String::length))
                            .orElse("");
                    sb.append("Found: ").append(count)
                            .append(System.lineSeparator());
                    sb.append("Max:").append(System.lineSeparator())
                            .append(max)
                            .append(System.lineSeparator());
                    sb.append("Min:")
                            .append(System.lineSeparator())
                            .append(min);
                    return sb.toString();
                }
        );
    }

    public StatsPrinter<BigInteger> createIntPrinter(ValueContainer<BigInteger> container) {
        return new StatsPrinter<>(
                container,
                list -> {
                    var sb = new StringBuilder();
                    long count = list.size();
                    var max = list.stream().max(BigInteger::compareTo).orElse(BigInteger.ZERO);
                    var min = list.stream().min(BigInteger::compareTo).orElse(BigInteger.ZERO);
                    BigInteger sum = list.stream().reduce(BigInteger.ZERO, BigInteger::add);
                    BigDecimal average = new BigDecimal(sum).divide(BigDecimal.valueOf(count), RoundingMode.FLOOR);
                    sb.append("Max: ").append(max);
                    sb.append(System.lineSeparator());
                    sb.append("Min: ").append(min);
                    sb.append(System.lineSeparator());
                    sb.append("Average: ").append(average);
                    sb.append(System.lineSeparator());
                    sb.append("Sum: ").append(sum);
                    return sb.toString();
                }
        );
    }

    public static StatsPrinter<Double> createDoublePrinter(ValueContainer<Double> container) {
        return new StatsPrinter<>(
                container,
                list -> {
                    var sb = new StringBuilder();
                    long count = list.size();
                    double max = list.stream()
                            .mapToDouble(Double::doubleValue)
                            .max()
                            .orElse(0);
                    double min = list.stream()
                            .mapToDouble(Double::doubleValue)
                            .min()
                            .orElse(0);
                    double average = list.stream()
                            .mapToDouble(Double::doubleValue)
                            .average()
                            .orElse(0);
                    sb.append("Max: ").append(max);
                    sb.append(System.lineSeparator());
                    sb.append("Min: ").append(min);
                    sb.append(System.lineSeparator());
                    sb.append("Average: ").append(average);
                    sb.append(System.lineSeparator());
                    return sb.toString();
                }
        );
    }
}
