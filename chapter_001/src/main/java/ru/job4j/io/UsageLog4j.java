package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        long l = 40;
        short s = 1;
        char c = 'c';
        boolean b = true;
        byte bt = 2;
        float fl = 4;
        double dble = 1.2;


        LOG.debug("User info name : {}, age : {}, long : {}, short : {}, char : {}, boolean : {}, byte : {}, float : {}, double : {}", name, age, l, s, c, b, bt, fl, dble);
    }
}
