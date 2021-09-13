package ru.job4j.design.isp;

public class Task111 extends Task {
    public Task111() {
        setName("1.1.1");
    }

    @Override
    public void action() {
        //logic for Task 1.1.1
    }

    @Override
    public String toString() {
        return "------Task " + super.getName();
    }
}
