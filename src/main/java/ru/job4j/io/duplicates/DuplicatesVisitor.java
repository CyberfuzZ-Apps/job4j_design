package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, String> files = new HashMap<>();
    private List<String> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(Files.size(file),
                file.getFileName().toString());
        if (files.putIfAbsent(property,
                file.toAbsolutePath().toString()) != null) {
            duplicates.add(files.get(property));
            duplicates.add(file.toAbsolutePath().toString());
        }
        return super.visitFile(file, attrs);
    }

    public List<String> getDuplicates() {
        return List.copyOf(duplicates);
    }
}
