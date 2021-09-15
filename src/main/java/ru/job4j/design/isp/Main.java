package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;

public class Main implements Menu, Print {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void addTaskToTask(Task task, Task where) {
        Task rsl = findTask(where.getName());
        rsl.addSubTask(task);
    }

    @Override
    public Task findTask(String name) {
        for (Task task : tasks) {
            Task rsl = findChildrenTasks(task, name);
            if (rsl != null) {
                return rsl;
            }
        }
        throw new IllegalArgumentException("Task not found!");
    }

    private Task findChildrenTasks(Task task, String name) {
        if (name.equals(task.getName())) {
            return task;
        }
        List<Task> taskList = task.getChildrenTasks();
        for (Task nestedTask : taskList) {
            if (name.equals(nestedTask.getName())) {
                return nestedTask;
            } else if (nestedTask.getChildrenTasks().size() > 0) {
                Task rsl = findChildrenTasks(nestedTask, name);
                if (rsl != null) {
                    return rsl;
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteTask(String name) {
        Task foundedTask = findTask(name);
        if (tasks.removeIf(task -> task.equals(foundedTask))) {
            return true;
        }
        for (Task t : tasks) {
            List<Task> children = t.getChildrenTasks();
            if (children.size() > 0) {
                if (deleteFromSubTask(children, foundedTask)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean deleteFromSubTask(List<Task> children, Task foundedTask) {
        if (children.removeIf(task -> task.equals(foundedTask))) {
            return true;
        }
        for (Task child : children) {
            List<Task> subList = child.getChildrenTasks();
            if (subList.size() > 0) {
                return deleteFromSubTask(subList, foundedTask);
            }
        }
        return false;
    }

    @Override
    public void print() {
        for (Task task : tasks) {
            System.out.println(task.getName());
            List<Task> children = task.getChildrenTasks();
            if (children.size() > 0) {
                StringBuilder sb = new StringBuilder();
                subPrint(children, sb);
            }
        }
    }

    private void subPrint(List<Task> children, StringBuilder sb) {
        for (Task task : children) {
            sb.append("---");
            sb.append(task.getName());
            System.out.println(sb);
            sb.delete(3, sb.length());
            List<Task> subList = task.getChildrenTasks();
            if (subList.size() > 0) {
                subPrint(subList, sb);
                sb.setLength(0);
            }
        }
    }

    public static void main(String[] args) {
        Main menu = new Main();
        Action action = new FirstAction();
        Task task1 = new Task("Task 1", action);
        Task task11 = new Task("Task 1.1", action);
        Task task12 = new Task("Task 1.2", action);
        Task task111 = new Task("Task 1.1.1", action);
        Task task112 = new Task("Task 1.1.2", action);
        Task task113 = new Task("Task 1.1.3", action);
        Task task121 = new Task("Task 1.2.1", action);
        Task task122 = new Task("Task 1.2.2", action);
        Task task123 = new Task("Task 1.2.3", action);
        Task task2 = new Task("Task 2", action);
        Task task21 = new Task("Task 2.1", action);
        Task task22 = new Task("Task 2.2", action);
        Task task211 = new Task("Task 2.1.1", action);
        Task task212 = new Task("Task 2.1.2", action);
        Task task213 = new Task("Task 2.1.3", action);
        Task task221 = new Task("Task 2.2.1", action);
        Task task222 = new Task("Task 2.2.2", action);
        Task task223 = new Task("Task 2.2.3", action);
        Task task3 = new Task("Task 3", action);
        Task task4 = new Task("Task 4", action);
        menu.addTask(task1);
        menu.addTask(task2);
        menu.addTask(task3);
        menu.addTask(task4);
        menu.addTaskToTask(task11, task1);
        menu.addTaskToTask(task12, task1);
        menu.addTaskToTask(task21, task2);
        menu.addTaskToTask(task22, task2);
        menu.addTaskToTask(task111, task11);
        menu.addTaskToTask(task112, task11);
        menu.addTaskToTask(task113, task11);
        menu.addTaskToTask(task121, task12);
        menu.addTaskToTask(task122, task12);
        menu.addTaskToTask(task123, task12);
        menu.addTaskToTask(task211, task21);
        menu.addTaskToTask(task212, task21);
        menu.addTaskToTask(task213, task21);
        menu.addTaskToTask(task221, task22);
        menu.addTaskToTask(task222, task22);
        menu.addTaskToTask(task223, task22);
        menu.print();
    }
}
