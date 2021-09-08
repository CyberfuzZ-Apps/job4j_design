package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return FoodUtils.getPercentPassed(food) > 100;
    }

    @Override
    public void addToStorage(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(foods);
    }
}
