package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    /**
     * Метод filter должен прочитать файл и вернуть строки,
     * где предпоследнее число - это 404.
     * @param file
     * @return
     */
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            in.lines()
                    .filter(s -> s.contains("404"))
                    .filter(s -> s.charAt(s.length() - 1) != '-')
                    .forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Метод должен записывать результат фильтрации в файл.
     * @param log
     * @param file
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            out.println(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
