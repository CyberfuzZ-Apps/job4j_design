package ru.job4j.design.isp;

public class Task11 extends Task {
    public Task11() {
        setName("1.1");
    }

    @Override
    public void action() {
        //logic for Task 1.1
    }

    @Override
    public String toString() {
        return "---Task " + super.getName();
    }
}
