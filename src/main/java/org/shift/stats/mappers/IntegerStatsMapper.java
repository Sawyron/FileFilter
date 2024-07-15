package org.shift.stats.mappers;

import org.shift.stats.models.IntegerStats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

public class IntegerStatsMapper implements Function<List<BigInteger>, IntegerStats> {
    @Override
    public IntegerStats apply(List<BigInteger> bigIntegers) {
        BigInteger max = bigIntegers.stream()
                .max(BigInteger::compareTo)
                .orElse(BigInteger.ZERO);
        BigInteger min = bigIntegers.stream()
                .min(BigInteger::compareTo)
                .orElse(BigInteger.ZERO);
        BigInteger sum = bigIntegers.stream()
                .reduce(BigInteger.ZERO, BigInteger::add);
        BigDecimal average = new BigDecimal(sum).divide(BigDecimal.valueOf(bigIntegers.size()), RoundingMode.FLOOR);
        return new IntegerStats(max, min, sum, average);
    }
}
