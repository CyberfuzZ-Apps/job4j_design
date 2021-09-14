package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    private final String name;
    private final Action action;
    private final List<Task> childrenTasks = new ArrayList<>();

    public Task(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public void addSubTask(Task task) {
        childrenTasks.add(task);
    }

    public void execute() {
        if (action != null) {
            action.execute();
            return;
        }
        throw new IllegalArgumentException("Action is null");
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder rsl = new StringBuilder(name + System.lineSeparator());
        if (childrenTasks.size() != 0) {
            for (Task task : childrenTasks) {
                rsl.append(task.toString());
            }
        }
        return rsl.toString();
    }
}
