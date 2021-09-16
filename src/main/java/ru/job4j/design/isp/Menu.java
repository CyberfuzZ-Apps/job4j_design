package ru.job4j.design.isp;

public interface Menu {
    void addTask(Task adding, Task toTask);
    Task findTask(String name);
    boolean deleteTask(String name, Task menu);
}
