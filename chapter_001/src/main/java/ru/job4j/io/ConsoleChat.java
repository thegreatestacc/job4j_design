package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final List<String> botAnswersList = new ArrayList<>();
    private final List<String> chatDialogList = new ArrayList<>();
    private boolean run = true;
    private boolean pause = false;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        readBotAnswers();
        while (run) {
            Scanner scanner = new Scanner(System.in);
            String readLine = scanner.nextLine();
            switch (readLine) {
                case OUT:
                    run = false;
                    System.out.println("program off");
                    break;
                case STOP:
                    pause = true;
                    System.out.println("pause");
                    continue;
                case CONTINUE:
                    pause = false;
                    System.out.println("continue");
                default:
                    int randomBotAnswer = (int) (Math.random() * botAnswersList.size());
                    String phrase = botAnswersList.get(randomBotAnswer);
                    chatDialogList.add("user wrote - " + readLine);
                    chatDialogList.add("bot answered - " + phrase + System.lineSeparator());
            }
        }
        output();
    }

    // read answers from botPhrases.txt
    public void readBotAnswers() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(botAnswersList::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // write dialog between user and bot
    public void output() {
        String log = "C:\\Users\\ARENA\\Desktop\\job4j\\job4j_design\\chapter_001\\src\\main\\java\\ru\\job4j\\data\\chatLog.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, StandardCharsets.UTF_8, true))) {
            for (String str : chatDialogList) {
                bw.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String chatLog = "C:\\Users\\ARENA\\Desktop\\job4j\\job4j_design\\chapter_001\\src\\main\\java\\ru\\job4j\\data\\chatLog.txt";
        String chatText = "C:\\Users\\ARENA\\Desktop\\job4j\\job4j_design\\chapter_001\\src\\main\\java\\ru\\job4j\\data\\botPhrases.txt";
        ConsoleChat chat = new ConsoleChat(chatLog, chatText);
        chat.run();
    }
}
