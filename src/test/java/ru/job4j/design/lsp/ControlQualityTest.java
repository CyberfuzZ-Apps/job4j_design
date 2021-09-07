package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenLess25PercentThenWarehouse() {
        ControlQuality controlQuality = new ControlQuality();
        LocalDate expiryDate = LocalDate.now().plusDays(80);
        LocalDate createDate = LocalDate.now().minusDays(20);
        Food food = new Milk("Milk", expiryDate, createDate, 50, 0);
        controlQuality.distribution(food);
        Storage storage = controlQuality.getStorage();
        assertThat(storage.getFoods(), is(List.of(food)));
        assertThat(storage.getClass().getName(), is(Warehouse.class.getName()));
    }

    @Test
    public void whenBetween25And75PercentThenShop() {
        ControlQuality controlQuality = new ControlQuality();
        LocalDate expiryDate = LocalDate.now().plusDays(50);
        LocalDate createDate = LocalDate.now().minusDays(50);
        Food food = new Meat("Meat", expiryDate, createDate, 300, 0);
        controlQuality.distribution(food);
        Storage storage = controlQuality.getStorage();
        assertThat(storage.getFoods(), is(List.of(food)));
        assertThat(storage.getClass().getName(), is(Shop.class.getName()));
    }

    @Test
    public void whenOver75PercentThenShopAndDiscount() {
        ControlQuality controlQuality = new ControlQuality();
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(90);
        Food food = new Bread("Bread", expiryDate, createDate, 30, 0);
        controlQuality.distribution(food);
        Storage storage = controlQuality.getStorage();
        assertThat(food.getDiscount(), is(50.0));
        assertThat(storage.getFoods(), is(List.of(food)));
        assertThat(storage.getClass().getName(), is(Shop.class.getName()));
    }

    @Test
    public void whenOver100PercentThenTrash() {
        ControlQuality controlQuality = new ControlQuality();
        LocalDate expiryDate = LocalDate.now().minusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(100);
        Food food = new Orange("Orange", expiryDate, createDate, 40, 0);
        controlQuality.distribution(food);
        Storage storage = controlQuality.getStorage();
        assertThat(storage.getFoods(), is(List.of(food)));
        assertThat(storage.getClass().getName(), is(Trash.class.getName()));
    }
}