package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws Exception {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dump))) {
            br.lines().map(i -> i.split(";"))
                    .forEach(i -> users.add(new User(i[0], i[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection con = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User u : users) {
                try (PreparedStatement statement = con.prepareStatement("insert into users (name, email) values (?, ?)")) {
                    statement.setString(1, u.name);
                    statement.setString(2, u.email);
                    statement.execute();
                }
            }
        }
    }

    private static class User {
        private String name, email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public static void main(String[] args) throws Exception {
            Properties cfg = new Properties();
            try (FileInputStream in = new FileInputStream("chapter_003/src/main/resources/spammer.properties")) {
                cfg.load(in);
            }
            ImportDB db = new ImportDB(cfg, "chapter_003/src/main/resources/dump.txt");
            db.save(db.load());
        }
    }
}
