package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(cachingDir + File.separator + key))) {
            reader.lines()
                    .forEach(sb::append);
            put(key, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
