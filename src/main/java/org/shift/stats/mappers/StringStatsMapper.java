package org.shift.stats.mappers;

import org.shift.stats.models.StringStats;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class StringStatsMapper implements Function<List<String>, StringStats> {
    @Override
    public StringStats apply(List<String> strings) {
        Comparator<String> lengthComparator = Comparator.comparing(String::length);
        String longest = strings.stream()
                .max(lengthComparator)
                .orElse("");
        String shortest = strings.stream()
                .min(lengthComparator)
                .orElse("");
        return new StringStats(longest, shortest);
    }
}
