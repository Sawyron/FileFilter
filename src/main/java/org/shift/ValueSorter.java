package org.shift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ValueSorter {
    private final List<ValueContainer<?>> containers;

    public ValueSorter(Collection<ValueContainer<?>> containers) {
        this.containers = new ArrayList<>(containers);
    }

    public void sort(InputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            reader.lines().forEach(this::tryAddValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void tryAddValue(String token) {
        for (ValueContainer<?> container : containers) {
            if (container.canParse(token)) {
                container.add(token);
                break;
            }
        }
    }
}
