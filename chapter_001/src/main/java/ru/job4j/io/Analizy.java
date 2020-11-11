package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> inputLines = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        String one;
        String two;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            in.lines().forEach(inputLines::add);
            for (String str : inputLines) {
                if (!str.isEmpty()) {
                    String[] split = str.split(" ");
                    stringList.add(split[0]);
                }
            }
            for (int i = 0; i < stringList.size(); i++) {
                one = stringList.get(i);
                two = stringList.get(++i);
                if ((one.equals("400") || one.equals("500")) && (two.equals("200") || two.equals("300"))) {
                    out.write("начало периода "  + one + "\n");
                    out.write("конец периода "  + two + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.txt");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
