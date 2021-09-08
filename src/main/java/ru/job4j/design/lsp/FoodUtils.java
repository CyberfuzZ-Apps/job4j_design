package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FoodUtils {

    public static double getPercentPassed(Food food) {
        double total = food.getCreateDate().until(food.getExpiryDate(), ChronoUnit.DAYS);
        double passed = food.getCreateDate().until(LocalDate.now(), ChronoUnit.DAYS);
        return passed / total * 100;
    }
}
