package org.shift;

import java.io.*;
import java.util.List;

public class ValueWriter<T> {
    private final ValueContainer<T> container;

    public ValueWriter(ValueContainer<T> container) {
        this.container = container;
    }

    public void write(OutputStream out) throws IOException {
        List<String> tokens = container.tokens().toList();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        for (String token : tokens) {
            writer.write(token);
        }
        writer.flush();
    }
}
