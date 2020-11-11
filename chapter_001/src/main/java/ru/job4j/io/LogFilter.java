package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            br.lines().forEach(lines::add);
            for (String str : lines) {
                String[] split = str.split(" ");
                if (split[split.length - 2].equals("404") && !split[split.length - 1].equals("-")) {
                    result.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
