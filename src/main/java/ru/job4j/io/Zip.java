package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (var p : sources) {
                zip.putNextEntry(new ZipEntry(p.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(p.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validateArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 3
                || argsName.get("d") == null
                || argsName.get("e") == null
                || argsName.get("o") == null) {
            throw new IllegalArgumentException("Неправильный ввод параметров! "
                    + "Используйте java -jar pack.jar "
                    + "-d=АРХИВИРУЕМАЯ_ДИРЕКТОРИЯ "
                    + "-e=ИСКЛЮЧАЕМЫЕ_ФАЙЛЫ "
                    + "-o=ИМЯ_АРХИВА");
        }
        if (!Files.exists(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Не существующая директория!");
        }
        return argsName;
    }

    public static void main(String[] args) throws IOException {
        ArgsName arguments = validateArgs(args);
        Path dir = Paths.get(arguments.get("d"));
        Path out = Paths.get(arguments.get("o"));
        if (!Files.isDirectory(dir)) {
            packSingleFile(dir, out);
        }
        List<Path> fileList = Search.search(dir,
                p -> !p.toFile().getName().endsWith(arguments.get("e")));
        packFiles(fileList, out);
    }
}
