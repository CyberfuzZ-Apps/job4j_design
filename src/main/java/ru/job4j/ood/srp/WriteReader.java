package ru.job4j.ood.srp;

/**
 * Здесь нарушение SRP - класс должен предоставлять только один функционал,
 * либо читать из файла, либо писать в файл, а тут и то и другое.
 * По принципу SRP надо этот класс разделить на два класса -
 * Reader и Writer.
 */
public class WriteReader {

    public void readFile() {
        //читает данные из файла
    }

    public void writeToFile() {
        //пишет данные в файл
    }
}
