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

    public static Connection newConnect(String FileName) throws SQLException {
        String newUrl = "jdbc:sqlite:" + FileName;
        Connection connection = DriverManager.getConnection(newUrl);
        return connection;
    }

    public static Connection connect(String FileName) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + FileName);
        return connection;
    }



}
