package ru.job4j.design.lsp;

import java.util.List;

public interface Storage {
    void addToStorage(Food food);
    List<Food> getFoods();
}
