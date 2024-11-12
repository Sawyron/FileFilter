package org.shift;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ValueWriterTest {

    @Test
    void whenGetNonEmptyContainer_thenWriteValuesOnSeparateLines() {
        ValueContainer<String> container = new ValueContainer<>(t -> true, token -> token);
        container.add("one");
        container.add("two");
        container.add("three");
        var writer = new ValueWriter(container);
        var output = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> writer.write(output));
        String result = output.toString();
        String expected = String.join(System.lineSeparator(), container.tokens().toList());
        expected += System.lineSeparator();
        assertEquals(expected, result);
        assertEquals(3, container.getSize());
    }

    @Test
    void whenContainerHasNoElements_thenIsEmptyTrue() {
        ValueContainer<String> container = new ValueContainer<>(t -> true, token -> token);
        var writer = new ValueWriter(container);
        assertTrue(writer.isEmpty());
    }
}