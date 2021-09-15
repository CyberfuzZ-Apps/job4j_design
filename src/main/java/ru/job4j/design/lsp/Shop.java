package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        double percentPassed = FoodUtils.getPercentPassed(food);
        if (percentPassed >= 25 && percentPassed <= 75) {
            return true;
        } else if (percentPassed > 75 && percentPassed <= 100) {
            food.setDiscount(50);
            return true;
        }
        return false;
    }

    @Override
    public void addToStorage(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }
}
