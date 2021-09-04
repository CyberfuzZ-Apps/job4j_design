package ru.job4j.ood.srp;

import java.util.Objects;

/**
 * Здесь нарушение SRP - в модели данных User присутствуют методы
 * с логикой работы с базой данных.
 */
public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    /* Нарушение SRP */
    public void saveToBD() {
        // логика сохранения в базу данных
    }

    /* Нарушение SRP */
    public void loadFromBD() {
        // логика загрузки из базы данных
    }
}
