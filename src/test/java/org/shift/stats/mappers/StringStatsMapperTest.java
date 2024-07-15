package org.shift.stats.mappers;

import org.junit.jupiter.api.Test;
import org.shift.stats.models.StringStats;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class StringStatsMapperTest {
  @Test
    void testApply() {
      var mapper = new StringStatsMapper();
      List<String> values = List.of("abc", "example", "test");
      StringStats stats = mapper.apply(values);
      assertEquals("abc", stats.shortest());
      assertEquals("example", stats.longest());
  }
}