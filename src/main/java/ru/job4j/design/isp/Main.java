package ru.job4j.design.isp;

import java.util.List;

/**
 * Класс содержит древовидную структуру "Меню" (Task).
 */
public class Main implements Menu, Print {
    private final Task menu = new Task("Menu:", null);
    private final StringBuilder prefix = new StringBuilder();

    /**
     * Метод добавляет пункт меню.
     * @param adding - добавляемый пункт меню.
     * @param toTask - в какой каталог добавить.
     */
    @Override
    public void addTask(Task adding, Task toTask) {
        Task rsl = findTask(toTask.getName());
        rsl.addSubTask(adding);
    }

    /**
     * Метод ищет пункт меню по его названию. Возвращает найденный пункт меню,
     * либо IllegalArgumentException - если пункт с таким названием не существует.
     *
     * @param name - название пункта меню.
     * @return - найденный пункт меню.
     * @throws IllegalArgumentException - если пункт не найден.
     */
    @Override
    public Task findTask(String name) throws IllegalArgumentException {
        if (name.equals(menu.getName())) {
            return menu;
        }
        for (Task task : menu.getChildrenTasks()) {
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

    /**
     * Метод удаляет пункт меню. Возвращает true если пункт меню удалён,
     * либо IllegalArgumentException - если пункт с таким названием не существует.
     *
     * @param name - название удаляемого пункта меню.
     * @param menu - корневой пункт меню.
     * @return - true - если пункт меню удален.
     * @throws IllegalArgumentException - если пункт с таким названием не существует.
     */
    @Override
    public boolean deleteTask(String name, Task menu) throws IllegalArgumentException {
        if (menu == this.menu) {
            findTask(name);
        }
        List<Task> list = menu.getChildrenTasks();
        if (list.removeIf(t -> t.getName().equals(name))) {
            return true;
        }
        for (Task task : list) {
            if (task.getChildrenTasks().size() > 0) {
                if (deleteTask(name, task)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод выводит в консоль список пунктов меню,
     * в древовидной структуре.
     *
     * @param menu - корневой каталог меню.
     */
    @Override
    public void print(Task menu) {
        if (menu == this.menu) {
            System.out.println(menu.getName());
        }
        List<Task> list = menu.getChildrenTasks();
        for (Task task : list) {
            System.out.println(prefix + task.getName());
            if (task.getChildrenTasks().size() > 0) {
                prefix.append("---");
                print(task);
                prefix.delete(0, 3);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
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
        main.addTask(task1, main.menu);
        main.addTask(task2, main.menu);
        main.addTask(task3, main.menu);
        main.addTask(task4, main.menu);
        main.addTask(task11, task1);
        main.addTask(task12, task1);
        main.addTask(task21, task2);
        main.addTask(task22, task2);
        main.addTask(task111, task11);
        main.addTask(task112, task11);
        main.addTask(task113, task11);
        main.addTask(task121, task12);
        main.addTask(task122, task12);
        main.addTask(task123, task12);
        main.addTask(task211, task21);
        main.addTask(task212, task21);
        main.addTask(task213, task21);
        main.addTask(task221, task22);
        main.addTask(task222, task22);
        main.addTask(task223, task22);
        main.addTask(new Task("Task 2.2.3.1", action), task223);
        main.print(main.menu);
    }
}
