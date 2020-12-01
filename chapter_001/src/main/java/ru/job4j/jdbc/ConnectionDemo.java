package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionDemo {
    public static Map<String, String> getApplicationProperties() {
        Map<String, String> result = new HashMap<>();
        List<String> list = new ArrayList<>();
        String path = "C:\\Users\\ARENA\\Desktop\\job4j\\job4j_design\\chapter_001\\src\\main\\resources\\app.properties";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(list::add);
            for (String str : list) {
                if (str.contains("=") ) {
                    String[] split = str.split("=");
                    result.put(split[0], split[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Map<String, String> applicationProperties = getApplicationProperties();
        Class.forName("org.postgresql.Driver");
        String url = applicationProperties.get("url");
        String login = applicationProperties.get("login");
        String password = applicationProperties.get("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
