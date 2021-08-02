package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Поиск файлов по критерию [#783]
 *
 * 1. Создать программу для поиска файла.
 * Все классы, относящиеся к этому заданию должны быть в отдельном пакете
 *
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 *
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 *
 * 4. Программа должна собираться в jar и запускаться через
 * java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 *
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска:
 * mask искать по маске,
 * name по полному совпадение имени,
 * regex по регулярному выражению.
 * -o - результат записать в файл.
 *
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */
public class FindFiles {

    public static void main(String[] args) throws IOException {
        FindFiles findFiles = new FindFiles();
        findFiles.search(args);
    }

    private void search(String[] args) throws IOException {
        Arguments arguments = Validates.validateArgs(args);
        Path dir = Paths.get(arguments.get("d"));
        Path target = Paths.get(arguments.get("o"));
        Predicate<Path> cond = condition(arguments.get("t"), arguments.get("n"));
        FindFileVisitor fileVisitor = new FindFileVisitor(cond);
        Files.walkFileTree(dir, fileVisitor);
        WriteFile.writeToFile(fileVisitor.getFiles(), target);
    }

    private Predicate<Path> condition(String type, String name) {
        return switch (type) {
            case "name" -> path -> name.equals(path.getFileName().toString());
            case "mask" -> path -> {
                Pattern pattern = Pattern.compile("\\..++");
                Matcher matcher = pattern.matcher(name);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Неверное расширение файла " + name);
                }
                return path.toFile().getName()
                        .endsWith(name.toLowerCase().substring(matcher.start()));
            };
            case "regex" -> path -> path.toFile().getName().matches(name);
            default -> null;
        };
    }
}
