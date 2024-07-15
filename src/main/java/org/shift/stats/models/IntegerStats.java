package org.shift.stats.models;

import java.math.BigDecimal;
import java.math.BigInteger;

public record IntegerStats(BigInteger max, BigInteger min, BigInteger sum, BigDecimal average) {
}
