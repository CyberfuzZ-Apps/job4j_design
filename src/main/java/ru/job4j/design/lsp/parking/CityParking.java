package ru.job4j.design.lsp.parking;

import java.util.HashMap;
import java.util.Map;

public class CityParking implements Parking {
    private int cars;
    private int trucks;
    private final Map<Automobile, String> mapAuto;

    public CityParking(int cars, int trucks) {
        this.cars = cars;
        this.trucks = trucks;
        mapAuto = new HashMap<>((cars + trucks) * 2);
    }

    @Override
    public boolean park(Automobile auto) {
        String value = "";
        if (auto.getSize() == 1) {
            if (cars < 1) {
                return false;
            }
            cars--;
            value = "Car";
        } else {
            if (trucks < 1 && cars < auto.getSize()) {
                return false;
            } else if (trucks >= 1) {
                trucks--;
                value = "Truck";
            } else if (cars >= auto.getSize()) {
                cars -= auto.getSize();
                value = "Car";
            }
        }
        return mapAuto.put(auto, value) == null;
    }

    @Override
    public boolean takeAuto(Automobile auto) {
        String value = mapAuto.get(auto);
        if (value == null) {
            return false;
        } else if ("Car".equals(value)) {
            cars += auto.getSize();
        } else if ("Truck".equals(value)) {
            trucks++;
        }
        return mapAuto.remove(auto) != null;
    }

    @Override
    public Map<String, Integer> freeSpaces() {
        return Map.of("Car", cars, "Truck", trucks);
    }
}
