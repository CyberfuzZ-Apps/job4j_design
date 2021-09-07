package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportDeveloper implements Report {
    private Store store;

    public ReportDeveloper(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        ReportEngine reportEngine = new ReportEngine(store);
        StringBuilder text = new StringBuilder();
        text.append("""
                <!DOCTYPE html>
                <html lang="ru">
                <head>
                  <meta charset="UTF-8">
                  <title>Document</title>
                  <meta name="descriptions" content="HTML разметка">
                  <meta name="keywords" content="html, lesson">
                </head>
                <body>
                """);
        text.append(reportEngine.generate(filter));
        text.append("""
                </body>
                </html>
                """);
        return text.toString();
    }
}
