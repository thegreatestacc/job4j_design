package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class.getName());
    public static void main(String[] args) {
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
                        if (strings[1].equals("/?msg=Exit")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Exit.".getBytes());
                            server.close();
                        }
                        if (strings[1].equals("/?msg=Hello")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello, dear friend.".getBytes());
                        }
                        if (!(strings[1].equals("/?msg=Exit")) && !(strings[1].equals("/?msg=Hello"))) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("What?".getBytes());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception in log", e);
        }
    }
}
