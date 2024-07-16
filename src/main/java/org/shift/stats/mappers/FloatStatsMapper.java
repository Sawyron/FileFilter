package org.shift.stats.mappers;

import org.shift.stats.models.FloatStats;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Function;

public class FloatStatsMapper implements Function<List<Double>, FloatStats> {
    @Override
    public FloatStats apply(List<Double> doubles) {
        DoubleSummaryStatistics stats = doubles.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();
        return new FloatStats(stats.getMax(), stats.getMin(), stats.getSum(), stats.getAverage());
    }
}
