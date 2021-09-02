package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getRsl(value, comparator, x -> x > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getRsl(value, comparator, x -> x < 0);
    }

    private <T> T getRsl(List<T> value, Comparator<T> comparator, Predicate<Integer> check) {
        T rsl = value.get(0);
        for (T val : value) {
            if (check.test(comparator.compare(val, rsl))) {
                rsl = val;
            }
        }
        return rsl;
    }
}
