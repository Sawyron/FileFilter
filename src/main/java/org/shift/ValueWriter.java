package org.shift;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class ValueWriter {
    private final ValueContainer<?> container;

    public ValueWriter(ValueContainer<?> container) {
        this.container = container;
    }

    public void write(OutputStream out) throws IOException {
        List<String> tokens = container.tokens().toList();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        for (String token : tokens) {
            writer.write(token);
            writer.newLine();
        }
        writer.flush();
    }

    public boolean isEmpty() {
        return container.getSize() == 0;
    }
}
