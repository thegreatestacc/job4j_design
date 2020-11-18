package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить, должны быть записаны в текстовый лог.
 * Т.е. класс принимает в конструктор 2 параметра - имя файла, в который будет записан весь диалог между ботом и пользователем,
 * и имя файла в котором находятся строки с ответами, которые будет использовать бот. Вам нужно реализовать метод run(),
 * логику получения ответа от бота из файла допускается вынести в отдельный метод.
 */

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
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(log)))) {
            for (String str : chatDialogList) {
                pw.write(str + System.lineSeparator());
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
