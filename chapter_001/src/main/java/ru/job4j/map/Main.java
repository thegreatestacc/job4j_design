package ru.job4j.map;

import ru.job4j.model.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Calendar calendar = new GregorianCalendar();
        Map<User, Object> map = new HashMap<>();

        User user1 = new User("name", 2, calendar);
        User user2 = new User("name", 2, calendar);

        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);
    }

}
