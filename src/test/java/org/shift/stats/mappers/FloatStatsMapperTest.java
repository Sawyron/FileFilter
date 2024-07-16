package org.shift.stats.mappers;

import org.junit.jupiter.api.Test;
import org.shift.stats.models.FloatStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloatStatsMapperTest {
    @Test
    void whenCommonCase_thenMapCorrectly() {
        var mapper = new FloatStatsMapper();
        List<Double> values = List.of(1.0, 2.0, 3.0);
        FloatStats stats = mapper.apply(values);
        double precision = 0.01;
        assertEquals(1.0, stats.min(), precision);
        assertEquals(3.0, stats.max(), precision);
        assertEquals(6.0, stats.sum(), precision);
        assertEquals(2.0, stats.average(), precision);
    }
}