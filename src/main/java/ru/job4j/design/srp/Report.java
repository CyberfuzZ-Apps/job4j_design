package ru.job4j.design.srp;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter, Store store);

    default StringBuilder defaultGenerate(Predicate<Employee> filter,
                                          Store store,
                                          StringBuilder text) {
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text;
    }
}
