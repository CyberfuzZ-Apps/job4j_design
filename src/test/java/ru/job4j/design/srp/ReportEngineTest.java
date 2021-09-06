package ru.job4j.design.srp;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new ReportOld());
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true, store), is(expect.toString()));
    }

    @Test
    public void whenBookkeepingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new ReportBookkeeping());
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(BigDecimal.valueOf(worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true, store), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 50);
        Employee worker3 = new Employee("Evgeniy", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngine(store, new ReportHR());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true, store), is(expect.toString()));
    }

    @Test
    public void whenDeveloperGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new ReportDeveloper());
        StringBuilder expect = new StringBuilder()
                .append("""
                        <!DOCTYPE html>
                        <html lang="ru">
                        <head>
                          <meta charset="UTF-8">
                          <title>Document</title>
                          <meta name="descriptions" content="HTML разметка">
                          <meta name="keywords" content="html, lesson">
                        </head>
                        <body>
                        """)
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("""
                        </body>
                        </html>
                        """);
        assertThat(engine.generate(em -> true, store), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 100);
        store.add(worker);
        Report engine = new ReportEngine(store, new ReportJSON());
        String expect = "{\"name\":\"Ivan\",\"salary\":100.0}\n";
        assertThat(engine.generate(em -> true, store), is(expect));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", null, null, 150);
        store.add(worker);
        Report engine = new ReportEngine(store, new ReportXML());
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employees>
                        <name>Ivan</name>
                        <salary>150.0</salary>
                    </employees>
                </employees>
                """;
        assertThat(engine.generate(em -> true, store), is(expect.toString()));
    }
}