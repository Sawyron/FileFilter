package org.shift;

import java.math.BigInteger;

public class ContainerFactories {
    public static ValueContainer<Double> getDoubleContainer() {
        return new ValueContainer<>(
                token -> {
                    try {
                        Double.valueOf(token);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                },
                Double::valueOf);
    }

    public static ValueContainer<BigInteger> getIntegerContainer() {
        return new ValueContainer<>(
                token -> {
                    try {
                        var value = new BigInteger(token);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                },
                BigInteger::new);
    }

    public static ValueContainer<String> getStringContainer() {
        return new ValueContainer<>(_ -> true, token -> token);
    }
}
