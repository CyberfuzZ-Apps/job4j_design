package ru.job4j.io.find;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Validates {
    public static Arguments validateArgs(String[] args) {
        Arguments arguments = Arguments.of(args);
        String hint = """
                            Используйте java -jar find.jar -d=... -n=... -t=... -o=...
                            где
                            -d=НАЧАЛЬНАЯ_ДИРЕКТОРИЯ
                            -n=ИМЯ_ФАЙЛА (имя, маска, регулярное выражение)
                            -t=ТИП_ПОИСКА (mask - искать по маске,
                                           name - по полному совпадению имени,
                                           regex - по регулярному выражению)
                            -o=ИМЯ_ВЫХОДНОГО_ФАЙЛА (с расширением)
                            """;
                if (args.length != 4
                        || arguments.get("d") == null
                        || arguments.get("n") == null
                        || arguments.get("t") == null
                        || arguments.get("o") == null) {
                    throw new IllegalArgumentException("Неправильный ввод параметров!"
                            + System.lineSeparator() + hint);
                }
                if (!Files.exists(Paths.get(arguments.get("d")))
                        || !Files.isDirectory(Paths.get(arguments.get("d")))) {
                    throw new IllegalArgumentException("Несуществующая директория!"
                            + System.lineSeparator() + hint);
                }
        if (!arguments.get("o").contains(".")) {
            throw new IllegalArgumentException("Неправильное расширение выходного файла"
                    + System.lineSeparator() + hint);
        }
        switch (arguments.get("t")) {
            case "mask":
            case "name":
            case "regex":
                break;
            default:
                throw new IllegalArgumentException("Неправильный ввод ТИПА_ПОИСКА"
                        + System.lineSeparator() + hint);
        }
        return arguments;
    }
}
