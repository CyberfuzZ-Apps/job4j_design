package ru.job4j.design.lsp.parking;

import java.util.Map;
import java.util.Set;

public class CityParking implements Parking {
    private int cars;
    private int trucks;
    private Set<Automobile> autos;

    public CityParking(int cars, int trucks) {
        this.cars = cars;
        this.trucks = trucks;
    }

    @Override
    public boolean park(Automobile auto) {
        return false;
    }

    @Override
    public boolean takeAuto(Automobile auto) {
        return false;
    }

    @Override
    public Map<String, Integer> freeSpaces() {
        return null;
    }
}
