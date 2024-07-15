package org.shift.stats.mappers;

import org.shift.stats.models.FloatStats;

import java.util.List;
import java.util.function.Function;

public class FloatStatsMapper implements Function<List<Double>, FloatStats> {
    @Override
    public FloatStats apply(List<Double> doubles) {
        double max = doubles.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);
        double min = doubles.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0);
        double sum = doubles.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        double average = doubles.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
        return new FloatStats(max, min, sum, average);
    }
}
