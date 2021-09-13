package ru.job4j.design.isp;

public class Task1 extends Task {
    public Task1() {
        setName("1");
    }

    @Override
    public void action() {
        //logic for Task 1
    }

    @Override
    public String toString() {
        return "Task " + super.getName();
    }
}
