package ru.job4j.design.isp;

public class Task121 extends Task {
    public Task121() {
        setName("1.2.1");
    }

    @Override
    public void action() {
        //logic for Task 1.2.1
    }

    @Override
    public String toString() {
        return "------Task " + super.getName();
    }
}
