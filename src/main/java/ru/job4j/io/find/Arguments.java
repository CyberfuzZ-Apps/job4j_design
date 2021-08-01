package ru.job4j.io.find;

import java.util.HashMap;
import java.util.Map;

public class Arguments {
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

    public static Arguments of(String[] args) {
        Arguments arguments = new Arguments();
        arguments.parse(args);
        return arguments;
    }
}
