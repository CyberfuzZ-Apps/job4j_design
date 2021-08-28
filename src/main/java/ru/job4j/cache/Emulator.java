package ru.job4j.cache;

/**
 * Предоставить пользователю возможности:
 *
 * - указать кэшируемую директорию
 *
 * - загрузить содержимое файла в кэш
 *
 * - получить содержимое файла из кэша
 */
public class Emulator {
    private AbstractCache<String, String> cache;

    public void dir(String dir) {
        cache = new DirFileCache(dir);
    }

    public void loadCache(String file) {
        cache.load(file);
    }

    public String getValue(String file) {
        return cache.get(file);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.dir("./data/for_cache");
        emulator.loadCache("Names.txt");
        emulator.loadCache("Address.txt");
        System.out.println("File - Names\n" + emulator.getValue("Names.txt"));
        System.out.println("File - Address\n" + emulator.getValue("Address.txt"));

    }
}
