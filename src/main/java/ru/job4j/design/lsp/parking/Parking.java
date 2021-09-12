package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean park(Automobile auto);
    boolean takeAuto(Automobile auto);
    int freeSpaces();
}
