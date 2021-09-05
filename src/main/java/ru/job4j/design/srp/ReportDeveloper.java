package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportDeveloper implements Report {
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
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
        defaultGenerate(filter, store, text);
        text.append("""
                </body>
                </html>
                """);
        return text.toString();
    }
}
