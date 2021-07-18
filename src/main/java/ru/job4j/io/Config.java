package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !(s.isEmpty() || s.charAt(0) == '#'))
                    .forEach(s -> {
                        if (s.contains("=")) {
                            String[] keyValue = s.split("=");
                            values.put(keyValue[0], keyValue.length == 2 ? keyValue[1] : null);
                            return;
                        }
                        throw new IllegalArgumentException();
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int size() {
        return values.size();
    }
}
