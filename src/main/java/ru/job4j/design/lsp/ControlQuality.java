package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {
    private Storage storage;

    public void distribution(Food food) {
        double total = food.getCreateDate().until(food.getExpiryDate(), ChronoUnit.DAYS);
        double passed = food.getCreateDate().until(LocalDate.now(), ChronoUnit.DAYS);
        double percentPassed = passed / total * 100;
        if (percentPassed < 25) {
            storage = new Warehouse();
        } else if (percentPassed < 75) {
            storage = new Shop();
        } else if (percentPassed <= 100) {
            food.setDiscount(50);
            storage = new Shop();
        } else {
            storage = new Trash();
        }
        storage.addToStorage(food);
    }

    public Storage getStorage() {
        return storage;
    }
}
