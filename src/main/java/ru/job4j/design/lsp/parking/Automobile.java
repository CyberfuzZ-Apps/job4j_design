package ru.job4j.design.lsp.parking;

import java.util.Objects;

public class Automobile {
    private final String carNumber;
    private final int size;

    public Automobile(String carNumber, int size) {
        this.carNumber = carNumber;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Automobile that = (Automobile) o;
        return Objects.equals(carNumber, that.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber);
    }
}