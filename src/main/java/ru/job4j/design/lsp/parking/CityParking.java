package ru.job4j.design.lsp.parking;

public class CityParking implements Parking {

    @Override
    public boolean park(Automobile auto) {
        return false;
    }

    @Override
    public boolean takeAuto(Automobile auto) {
        return false;
    }

    @Override
    public int freeSpaces() {
        return 0;
    }
}
