package ru.job4j.design.isp;

import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Set<Task> set = new TreeSet<>();
        set.add(new Task1());
        set.add(new Task2());
        set.add(new Task11());
        set.add(new Task12());
        set.add(new Task111());
        set.add(new Task112());
        set.add(new Task121());
        set.add(new Task122());
        for (Task s : set) {
            System.out.println(s);
        }
    }
}
