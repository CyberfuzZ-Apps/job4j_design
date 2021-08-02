package ru.job4j.io.find;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class WriteFile {
    public static void writeToFile(List<String> files, Path target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target.toFile()))) {
            for (var p : files) {
                writer.write(p);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
