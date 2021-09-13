package ru.job4j.design.lsp.parking;

import java.util.Map;

public interface Parking {
    boolean park(Automobile auto);
    boolean takeAuto(Automobile auto);
    Map<String, Integer> freeSpaces();
}
