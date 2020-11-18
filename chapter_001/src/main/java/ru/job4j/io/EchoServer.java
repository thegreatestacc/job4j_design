package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    List<String> inputList = new ArrayList<>();
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        inputList.add(str);
                        System.out.println(str);
                    }
                    String s = inputList.get(0);
                    if (s.contains("=")) {
                        String[] strings = s.split(" ");
                        if (strings[1].equals("/?msg=Bye")) {
                            server.close();
                        }
                    }
                    out.write("HTTP/1.1 200 OK \r\n\\".getBytes());
                }
            }
        }
    }
}
