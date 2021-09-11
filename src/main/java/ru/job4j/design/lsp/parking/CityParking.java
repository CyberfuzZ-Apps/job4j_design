package ru.job4j.design.lsp.parking;

public class CityParking implements Parking {
    private int[] space;
    private Automobile[] automobile;

    @Override
    public boolean park() {
        return false;
    }

    @Override
    public int freeSpaces() {
        return 0;
    }
}
