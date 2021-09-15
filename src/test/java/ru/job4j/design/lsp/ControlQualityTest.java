package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenLess25PercentThenWarehouse() {
        Storage trash = new Trash();
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        List<Storage> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().plusDays(80);
        LocalDate createDate = LocalDate.now().minusDays(20);
        Food food = new Milk("Milk", expiryDate, createDate, 50, 0);
        controlQuality.distribution(food);
        assertThat(warehouse.getFoods(), is(List.of(food)));
    }

    @Test
    public void whenBetween25And75PercentThenShop() {
        Storage trash = new Trash();
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        List<Storage> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().plusDays(50);
        LocalDate createDate = LocalDate.now().minusDays(50);
        Food food = new Meat("Meat", expiryDate, createDate, 300, 0);
        controlQuality.distribution(food);
        assertThat(shop.getFoods(), is(List.of(food)));
    }

    @Test
    public void whenOver75PercentThenShopAndDiscount() {
        Storage trash = new Trash();
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        List<Storage> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(90);
        Food food = new Bread("Bread", expiryDate, createDate, 30, 0);
        controlQuality.distribution(food);
        assertThat(food.getDiscount(), is(50.0));
        assertThat(shop.getFoods(), is(List.of(food)));
    }

    @Test
    public void whenOver100PercentThenTrash() {
        Storage trash = new Trash();
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        List<Storage> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().minusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(100);
        Food food = new Orange("Orange", expiryDate, createDate, 40, 0);
        controlQuality.distribution(food);
        assertThat(trash.getFoods(), is(List.of(food)));
    }

    @Test
    public void whenResort() {
        Storage trash = new Trash();
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        List<Storage> storages = List.of(
                trash, shop, warehouse
        );
        ControlQuality controlQuality = new ControlQuality(storages);
        LocalDate expiryDate = LocalDate.now().minusDays(10);
        LocalDate createDate = LocalDate.now().minusDays(100);
        Food food = new Milk("Milk", expiryDate, createDate, 59.0, 0.0);
        warehouse.addToStorage(food);
        assertThat(warehouse.getFoods(), is(List.of(food)));
        controlQuality.resort();
        assertThat(warehouse.getFoods(), is(List.of()));
        assertThat(trash.getFoods(), is(List.of(food)));
    }
}