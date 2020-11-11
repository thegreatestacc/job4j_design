package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("app.properties"))) {
            br.lines().forEach(list::add);
            for (String str : list) {
                if (str.contains("=") ) {
                    String[] split = str.split("=");
                    values.put(split[0], split[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String result = "";
        for (Map.Entry<String, String> item : values.entrySet()) {
            if (item.getKey().equals(key)) {
                result = item.getValue();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.value("hibernate.connection.driver_class"));

        //System.out.println(new Config("app.properties"));
    }
}
