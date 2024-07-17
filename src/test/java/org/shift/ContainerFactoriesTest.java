package org.shift;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerFactoriesTest {
    @Test
    void whenFloatInScientificNotation_thenAcceptIt() {
        String token = "1.528535047E-25";
        ValueContainer<Double> container = ContainerFactories.getDoubleContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }

    @Test
    void whenFloatInCommonNotation_thenParseIt() {
        String token = "3.1415";
        ValueContainer<Double> container = ContainerFactories.getDoubleContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }

    @Test
    void whenFloatInIncorrectForm_thenDoNotAcceptIt() {
        String token = "1.528535047-25";
        ValueContainer<Double> container = ContainerFactories.getDoubleContainer();
        assertFalse(container.canParse(token));
        assertThrows(IllegalArgumentException.class, () -> container.add(token));
        assertEquals(0, container.getSize());
    }

    @Test
    void whenIntInCommonNotation_thenAcceptIt() {
        String token = "123";
        ValueContainer<BigInteger> container = ContainerFactories.getIntegerContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }

    @Test
    void whenIntIsBig_thenDoNotThrowException() {
        String token = "1234567890123456789";
        ValueContainer<BigInteger> container = ContainerFactories.getIntegerContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
        List<BigInteger> values = container.values().toList();
        assertEquals(1, values.size());
        assertEquals(new BigInteger(token), values.getFirst());
    }

    @Test
    void whenIntStartsWithZero_thenAcceptIt() {
        String token = "0123";
        ValueContainer<BigInteger> container = ContainerFactories.getIntegerContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
        List<BigInteger> values = container.values().toList();
        assertEquals(1, values.size());
        assertEquals(BigInteger.valueOf(123), values.getFirst());
    }
}