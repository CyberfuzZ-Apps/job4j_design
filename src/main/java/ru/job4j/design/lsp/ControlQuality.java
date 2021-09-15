package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void distribution(Food food) {
        for (Storage st : storages) {
            if (st.accept(food)) {
                st.addToStorage(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Storage storage : storages) {
            List<Food> f = storage.getFoods();
            foods.addAll(f);
            f.removeAll(f);
        }
        for (Food food : foods) {
            distribution(food);
        }
    }

}
