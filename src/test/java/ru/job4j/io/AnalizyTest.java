package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable1() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.printf("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    500 10:59:01
                    400 11:01:02
                    200 11:02:02
                    """);
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines()
                    .forEach(s -> sb.append(s).append("\n"));
        }
        assertThat(sb.toString(), is(
                """
                        10:57:01;11:02:02;
                        """));
    }

    @Test
    public void unavailable2() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    200 10:59:01
                    500 11:01:02
                    200 11:02:02
                    """);
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines()
                    .forEach(s -> sb.append(s).append("\n"));
        }
        assertThat(sb.toString(), is("""
                10:57:01;10:59:01;
                11:01:02;11:02:02;
                """));
    }
}