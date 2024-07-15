package org.shift;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ContainerFactoriesTest {
    @Test
    void testDoubleScientificParsing() {
        String token = "1.528535047E-25";
        ValueContainer<Double> container = ContainerFactories.getDoubleContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }

    @Test
    void testDoubleParsing() {
        String token = "3.1415";
        ValueContainer<Double> container = ContainerFactories.getDoubleContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }

    @Test
    void testInvalidDoubleParsing() {
        String token = "1.528535047-25";
        ValueContainer<Double> container = ContainerFactories.getDoubleContainer();
        assertFalse(container.canParse(token));
        assertThrows(IllegalArgumentException.class, () -> container.add(token));
        assertEquals(0, container.getSize());
    }

    @Test
    void testIntParsing() {
        String token = "123";
        ValueContainer<BigInteger> container = ContainerFactories.getIntegerContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }

    @Test
    void testBigIntParsing() {
        String token = "1234567890123456789";
        ValueContainer<BigInteger> container = ContainerFactories.getIntegerContainer();
        assertTrue(container.canParse(token));
        assertDoesNotThrow(() -> container.add(token));
        assertEquals(1, container.getSize());
    }
}