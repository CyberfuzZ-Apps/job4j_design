package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        double total = food.getCreateDate().until(food.getExpiryDate(), ChronoUnit.DAYS);
        double passed = food.getCreateDate().until(LocalDate.now(), ChronoUnit.DAYS);
        double percentPassed = passed / total * 100;
        return percentPassed < 25;
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
