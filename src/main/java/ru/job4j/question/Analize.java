package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        int added = 0;
        int changed = 0;
        int deleted = 0;
        for (var u : previous) {
            map.put(u.getId(), u.getName());
        }
        for (var u : current) {
            if (!map.containsKey(u.getId())) {
                added++;
            } else if (!map.get(u.getId()).equals(u.getName())) {
                changed++;
                map.remove(u.getId());
            }
            map.remove(u.getId());
        }
        deleted = map.size();
        return new Info(added, changed, deleted);
    }
}
