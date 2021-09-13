package ru.job4j.design.isp;

public class Task2 extends Task {
    public Task2() {
        setName("2");
    }

    @Override
    public void action() {
        //logic for Task 2
    }

    @Override
    public String toString() {
        return "Task " + super.getName();
    }
}
