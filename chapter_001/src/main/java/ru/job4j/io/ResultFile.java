package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("result.txt")){
            fileOutputStream.write("hello world!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
