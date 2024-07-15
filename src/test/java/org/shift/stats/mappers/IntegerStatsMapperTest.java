package org.shift.stats.mappers;

import org.junit.jupiter.api.Test;
import org.shift.stats.models.IntegerStats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerStatsMapperTest {
    @Test
    void testApply() {
        IntegerStatsMapper mapper = new IntegerStatsMapper();
        List<BigInteger> values = List.of(
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        );
        IntegerStats stats = mapper.apply(values);
        assertEquals(BigInteger.valueOf(3), stats.max());
        assertEquals(BigInteger.valueOf(1), stats.min());
        assertEquals(BigInteger.valueOf(6), stats.sum());
        assertEquals(0, BigDecimal.valueOf(2).compareTo(stats.average()));
    }

    @Test
    void testApplyWithBigNumbers() {
        IntegerStatsMapper mapper = new IntegerStatsMapper();
        List<BigInteger> values = List.of(
                new BigInteger("1234567890123456789"),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        );
        IntegerStats stats = mapper.apply(values);
        assertEquals(new BigInteger("1234567890123456789"), stats.max());
        assertEquals(BigInteger.valueOf(2), stats.min());
        assertEquals(new BigInteger("1234567890123456794"), stats.sum());
        var mc = new MathContext(6, RoundingMode.FLOOR);
        assertEquals(0, new BigDecimal("4.1152263004115226e+17")
                .setScale(6, RoundingMode.FLOOR)
                .round(mc)
                .compareTo(stats.average()
                        .setScale(6, RoundingMode.FLOOR)
                        .round(mc)));
    }
}