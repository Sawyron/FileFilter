package org.shift;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueSorterTest {
    @Test
    void whenConfigured_thenSortCorrectly() {
        ValueContainer<Double> floatValueContainer = ContainerFactories.getDoubleContainer();
        ValueContainer<BigInteger> integerValueContainer = ContainerFactories.getIntegerContainer();
        ValueContainer<String> stringValueContainer = ContainerFactories.getStringContainer();
        var sorter = new ValueSorter(List.of(integerValueContainer, floatValueContainer, stringValueContainer));
        String text = """
                Lorem ipsum dolor sit amet
                45
                Пример
                3.1415
                consectetur adipiscing
                -0.001
                тестовое задание
                100500
                Нормальная форма числа с плавающей запятой
                1.528535047E-25
                Long
                1234567890123456789
                """;
        var in = new ByteArrayInputStream(text.getBytes());
        sorter.sort(in);
        assertEquals(3, integerValueContainer.getSize());
        assertEquals(3, floatValueContainer.getSize());
        assertEquals(6, stringValueContainer.getSize());
    }
}