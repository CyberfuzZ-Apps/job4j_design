package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    /**
     * Инициализирует подключение к базе данных
     */
    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выполняет запрос в базу данных
     *
     * @param sql - форматированная строка с sql запросом
     */
    private void stExecute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает пустую таблицу без столбцов с указанным именем
     *
     * @param tableName - имя таблицы
     */
    public void createTable(String tableName) {
        String sql = String.format(
                "create table %s();", tableName);
        stExecute(sql);
    }

    /**
     * Удаляет таблицу по указанному имени
     *
     * @param tableName - имя таблицы
     */
    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table %s;", tableName);
        stExecute(sql);
    }

    /**
     * Добавляет столбец в таблицу
     *
     * @param tableName - имя таблицы
     * @param columnName - имя столбца
     * @param type - тип столбца
     */
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add column %s %s;",
                tableName, columnName, type);
        stExecute(sql);
    }

    /**
     * Удаляет столбец из таблицы
     *
     * @param tableName - имя таблицы
     * @param columnName - имя столбца
     */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s;", tableName, columnName);
        stExecute(sql);
    }

    /**
     * Переименовывает столбец
     *
     * @param tableName - имя таблицы
     * @param columnName - старое имя столбца
     * @param newColumnName - новое имя столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName);
        stExecute(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
