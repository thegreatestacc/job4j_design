package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        try (var statement = connection.createStatement()) {
            var sql = String.format(
                    "create table if not exist %s (%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar"
            );
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String table) {
        try (var statement = connection.createStatement()) {
            var sql = String.format(
                    "drop table %s",
                    table
            );
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (var statement = connection.createStatement()) {
            var sql = String.format(
                    "alter table %s add %s, %s",
                    tableName, columnName, type
            );
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (var statement = connection.createStatement()) {
            var sql = String.format(
                    "alter table %s drop column %s",
                    tableName, columnName
            );
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (var statement = connection.createStatement()) {
            var sql = String.format(
                    "alter table %s rename column %s to %s",
                    tableName, columnName, newColumnName
            );
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getScheme(String tableName) throws SQLException {
        var scheme = new StringBuilder();
        var metaData = connection.getMetaData();
        try (var columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    private Connection getConnection() throws Exception {
        var applicationProperties = getApplicationProperties();
        var url = applicationProperties.getProperty("url");
        properties.setProperty("login", applicationProperties.getProperty("login"));
        properties.setProperty("password", applicationProperties.getProperty("password"));
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, properties);
    }

    public static Properties getApplicationProperties() {
        Map<String, String> result = new HashMap<>();
        List<String> list = new ArrayList<>();
        Properties properties = new Properties();
        var path = "chapter_001/src/main/resources/app.properties";
        try (var br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(list::add);
            for (String str : list) {
                if (str.contains("=")) {
                    String[] split = str.split("=");
                    result.put(split[0], split[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty("url", result.get("url"));
        properties.setProperty("login", result.get("login"));
        properties.setProperty("password", result.get("password"));
        return properties;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
