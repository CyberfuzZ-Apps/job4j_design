package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String start = "";
            String end = "";
            String line = reader.readLine();
            while (line != null) {
                String[] str = line.split(" ");
                if (("400".equals(str[0]) || "500".equals(str[0]))
                        && start.isEmpty()) {
                    start = str[1];
                } else if (("200".equals(str[0]) || "300".equals(str[0]))
                        && !start.isEmpty()) {
                    end = str[1];
                    writer.printf("%s%n", start + ";" + end + ";");
                    start = "";
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        String source = "/Users/cyberfuzz/projects/job4j_design/data/server1.log";
        String target = "/Users/cyberfuzz/projects/job4j_design/data/analize_server_log1.csv";
        analizy.unavailable(source, target);
        source = "/Users/cyberfuzz/projects/job4j_design/data/server2.log";
        target = "/Users/cyberfuzz/projects/job4j_design/data/analize_server_log2.csv";
        analizy.unavailable(source, target);
    }
}
