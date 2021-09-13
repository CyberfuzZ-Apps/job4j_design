package ru.job4j.design.isp;

public class Task112 extends Task {
    public Task112() {
        setName("1.1.2");
    }

    @Override
    public void action() {
        //logic for Task 1.1.2
    }

    @Override
    public String toString() {
        return "------Task " + super.getName();
    }
}
