package org.shift.services;

import org.shift.ValueWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileWriteService {
    private record WriterEntry(ValueWriter<?> writer, String name) {
    }

    private final String prefix;

    public FileWriteService(String prefix, Path folder, boolean append) {
        this.prefix = prefix;
        this.folder = folder;
        this.append = append;
    }

    private final Path folder;
    private final boolean append;
    private final List<WriterEntry> entries = new ArrayList<>();

    public void write() throws IOException {
        for (WriterEntry entry : entries) {
            if (entry.writer.isEmpty()) {
                continue;
            }
            Path file = folder.resolve(Path.of(prefix + entry.name));
            try (var out = new FileOutputStream(file.toFile(), append)) {
                entry.writer.write(out);
            }
        }
    }

    public void addWriter(String name, ValueWriter<?> writer) {
        entries.add(new WriterEntry(writer, name));
    }
}
