package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportJSON implements Report {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        for (Employee e : store.findBy(filter)) {
            text.append(gson.toJson(e));
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}
