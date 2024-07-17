package org.shift.services;

import org.shift.ValueSorter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class FileProcessService {
    private final ValueSorter sorter;

    public FileProcessService(ValueSorter sorter) {
        this.sorter = sorter;
    }

    public void processFiles(Iterable<Path> paths) {
        for (Path path : paths) {
            try (FileInputStream in = new FileInputStream(path.toFile())) {
                sorter.sort(in);
                System.out.println("File processed: " + path);
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
