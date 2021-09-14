package ru.job4j.design.isp;

public class Main {
    public static void main(String[] args) {
        Action action = new FirstAction();
        Task task1 = new Task("Task 1", action);
        Task task11 = new Task("---Task 1.1", action);
        Task task12 = new Task("---Task 1.2", action);
        Task task111 = new Task("------Task 1.1.1", action);
        Task task112 = new Task("------Task 1.1.2", action);
        Task task113 = new Task("------Task 1.1.3", action);
        Task task121 = new Task("------Task 1.2.1", action);
        Task task122 = new Task("------Task 1.2.2", action);
        Task task123 = new Task("------Task 1.2.3", action);
        task11.addSubTask(task111);
        task11.addSubTask(task112);
        task11.addSubTask(task113);
        task12.addSubTask(task121);
        task12.addSubTask(task122);
        task12.addSubTask(task123);
        task1.addSubTask(task11);
        task1.addSubTask(task12);
        Task task2 = new Task("Task 2", action);
        System.out.print(task1);
        System.out.println(task2);
    }
}
