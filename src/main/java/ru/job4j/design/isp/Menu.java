package ru.job4j.design.isp;

public interface Menu {
    void addTask(Task task);
    void addTaskToTask(Task task, Task where);
    Task findTask(String name);
    boolean deleteTask(String name);
}
