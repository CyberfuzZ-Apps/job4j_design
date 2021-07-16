package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
