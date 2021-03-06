package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет параметров!");
        }
        for (String arg : args) {
            if (arg.charAt(0) != '-') {
                throw new IllegalArgumentException("Не правильный ввод ключа! "
                        + "Используйте -КЛЮЧ=ЗНАЧЕНИЕ");
            }
            if (arg.contains("=")) {
                String[] keyValue = arg.substring(1).split("=");
                if (keyValue.length < 2) {
                    throw new IllegalArgumentException("Не правильный ввод значения! "
                            + "Используйте -КЛЮЧ=ЗНАЧЕНИЕ");
                }
                values.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
