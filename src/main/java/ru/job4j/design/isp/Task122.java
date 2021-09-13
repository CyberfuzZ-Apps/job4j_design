package ru.job4j.design.isp;

public class Task122 extends Task {
    public Task122() {
        setName("1.2.2");
    }

    @Override
    public void action() {
        //logic for Task 1.2.2
    }

    @Override
    public String toString() {
        return "------Task " + super.getName();
    }
}
