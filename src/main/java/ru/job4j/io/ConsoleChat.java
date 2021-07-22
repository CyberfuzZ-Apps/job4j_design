package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> listAnswers = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private String getBotAnswer() {
        int index = (int) (Math.random() * listAnswers.size());
        return listAnswers.get(index);
    }

    private void readAnswers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines()
                    .forEach(listAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeChat(List<String> recordList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (var s : recordList) {
                writer.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        readAnswers();
        List<String> recordList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String welcome = """
                Добро пожаловать в чат! Чем я могу Вам помочь?
                      ("стоп" - приостановить общение,
                     "продолжить" - продолжить общение,
                       "закончить" - выйти из чата)
                """;
        System.out.println(welcome);
        recordList.add(welcome);
        String input = scanner.nextLine();
        recordList.add(input);
        boolean listen = true;
        while (!OUT.equals(input)) {
            if (STOP.equals(input)) {
                input = scanner.nextLine();
                recordList.add(input);
                listen = false;
                continue;
            }
            if (CONTINUE.equals(input)) {
                System.out.println("Продолжаем общение.");
                recordList.add("Продолжаем общение.");
                input = scanner.nextLine();
                recordList.add(input);
                listen = true;
                continue;
            }
            if (!listen) {
                input = scanner.nextLine();
                recordList.add(input);
                continue;
            }
            String answer = getBotAnswer();
            System.out.println(answer);
            recordList.add(answer);
            input = scanner.nextLine();
            recordList.add(input);
        }
        writeChat(recordList);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/log_console_chat.txt",
                "./data/bot_answers.txt");
        cc.run();
    }
}
