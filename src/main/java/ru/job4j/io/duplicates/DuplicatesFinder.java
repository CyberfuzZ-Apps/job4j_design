package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не указан путь к начальной директории!");
        }
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(args[0]), duplicatesVisitor);
        List<String> rsl = duplicatesVisitor.getDuplicates();
        System.out.println("Дубликаты :");
        for (var f : rsl) {
            System.out.println(f);
        }
    }
}
