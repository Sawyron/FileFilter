package org.shift.stats.mappers;

import org.junit.jupiter.api.Test;
import org.shift.stats.models.StringStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringStatsMapperTest {
    @Test
    void whenCommonCase_thenMapCorrectly() {
        var mapper = new StringStatsMapper();
        List<String> values = List.of("abc", "example", "test");
        StringStats stats = mapper.apply(values);
        assertEquals("abc", stats.shortest());
        assertEquals("example", stats.longest());
    }
}