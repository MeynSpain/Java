package com.example.databasetest;

import java.sql.*;

/**
 * Создает подключение к базе данных
 */
public class DB_Connect {

    private static String url = "jdbc:sqlite:DB_test.db";

    /**
     * Возвращает уже готовый connection
     * @return подключение к базе
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException
    {
        Connection connection = DriverManager.getConnection(url);
        return connection;
    }



}
