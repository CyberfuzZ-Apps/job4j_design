package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private final Path path;
    private final String delimiter;
    private final String out;
    private final List<String> filters;
    private final List<String> readData = new ArrayList<>();

    public CSVReader(Path path, String delimiter, String out, List<String> filters) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filters = filters;
    }

    public void reader() {
        if (filters.size() == 1 && "all".equals(filters.get(0))) {
            noFiltersReader();
            return;
        }
        List<Integer> indexes = filterIndexes();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(delimiter);
                for (int i : indexes) {
                    readData.add(String.format("%s", line[i] + " "));
                }
                readData.add(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void noFiltersReader() {
        try (Scanner scanner = new Scanner(path)) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                readData.add(String.format("%s", scanner.next() + " "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> filterIndexes() {
        List<Integer> columnIndexes = new ArrayList<>();
        try (Scanner scanner = new Scanner(path)) {
            List<String> columns = Arrays.asList(scanner.nextLine().split(delimiter));
            for (var s : filters) {
                int index = columns.indexOf(s);
                if (index == -1) {
                    throw new IllegalArgumentException("Столбец не найден!");
                }
                columnIndexes.add(index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnIndexes;
    }

    private void stdOut() {
        for (var s : readData) {
            System.out.print(s);
        }
    }

    private void writeInFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
            for (var s : readData) {
                writer.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validateArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 4
                || argsName.get("path") == null
                || argsName.get("delimiter") == null
                || argsName.get("out") == null
                || argsName.get("filter") == null) {
            throw new IllegalArgumentException("Неправильный ввод параметров! "
                    + "Используйте java -jar csvReader.jar "
                    + "-path=ПУТЬ_К_CSV_ФАЙЛУ "
                    + "-delimiter=РАЗДЕЛИТЕЛЬ(в кавычках) "
                    + "-out=ПРИЁМНИК_ДАННЫХ(stdout или путь_к_файлу) "
                    + "-filter=ФИЛЬТР_ПО_СТОЛБЦАМ(через запятую или all - без фильтра)");
        }
        if (!Files.exists(Paths.get(argsName.get("path")))
                || Files.isDirectory(Paths.get(argsName.get("path")))
                || !argsName.get("path").endsWith("csv")) {
            throw new IllegalArgumentException("Исходный файл не найден!");
        }
        return argsName;
    }

    public static void main(String[] args) {
        ArgsName arguments = validateArgs(args);
        CSVReader csvReader = new CSVReader(
                Paths.get(arguments.get("path")),
                arguments.get("delimiter"),
                arguments.get("out"),
                Arrays.asList(arguments.get("filter").split(",")));
        csvReader.reader();
        if (!"stdout".equals(csvReader.out)) {
            csvReader.writeInFile();
            return;
        }
        csvReader.stdOut();
    }
}
