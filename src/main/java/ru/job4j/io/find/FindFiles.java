package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

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
        String name = arguments.get("n");
        Predicate<Path> condition = switch (arguments.get("t")) {
            case "mask" -> path -> path.toFile().getName().endsWith(name.substring(1));
            case "regex", "name" -> path -> path.endsWith(name);
            default -> null;
        };
        FindFileVisitor fileVisitor = new FindFileVisitor(condition);
        Files.walkFileTree(dir, fileVisitor);
        WriteFile.writeToFile(fileVisitor.getFiles(), target);
    }
}
