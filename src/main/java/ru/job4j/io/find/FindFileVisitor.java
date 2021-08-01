package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindFileVisitor extends SimpleFileVisitor<Path> {
    final private Predicate<Path> condition;
    final private List<String> files = new ArrayList<>();

    public FindFileVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<String> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            files.add(file.toAbsolutePath().toString());
        }
        return super.visitFile(file, attrs);
    }
}
