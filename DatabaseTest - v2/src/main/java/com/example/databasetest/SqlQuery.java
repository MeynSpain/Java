package com.example.databasetest;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQuery {

    private String select = "SELECT * FROM \"Enterprise\"";
    private PreparedStatement preparedStatement = null;

    /**
     * Удаляет выбранный ObservableList<Enterpise>
     * @param connection
     * @param list
     */
    void DELETE(Connection connection, ObservableList<Enterpise> list )
    {
        //Удаление записей с одного id по другое
        String sql_delete = "DELETE FROM Enterprise WHERE id = ?;";

        try {

            //Пройдемся по листу и удалим каждую запись
            for(Enterpise enterpise: list)
            {
                //Запрос на удаление
                preparedStatement = connection.prepareStatement(sql_delete);

                //Подставляем нужный индекс
                preparedStatement.setInt(1, enterpise.id);

                //Обновляем и закрываем стейтмент
                preparedStatement.executeUpdate();
                preparedStatement.close();

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    /**
     * Обновление записи в базе
     * @param connection
     * @param enterpise
     */
    void UPDATE(Connection connection, Enterpise enterpise)
    {
        String sql_update = "UPDATE Enterprise " +
                "SET name = ?, banking_details = ?, contact_person = ? " +
                "where id = ?;";

        try {
            //Коннектимся и делаем запрос на обновление
            preparedStatement = connection.prepareStatement(sql_update);

            //Подставляем нужные данные
            preparedStatement.setString(1, enterpise.name);
            preparedStatement.setString(2, enterpise.banking_details);
            preparedStatement.setString(3, enterpise.contact_person);
            preparedStatement.setInt(   4, enterpise.id);

            //Обновляем и закрываем стейтмент
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


    String getSelect()
    {
        return select;
    }

    String getInsert(String name, String bank_details, String contact_person)
    {
        return "INSERT INTO \"Enterprise\" (name, banking_details, contact_person) " +
                "VALUES ('" + name + "', '"+bank_details+"','"+contact_person+"')";
    }

    String getDelete(Integer id)
    {
        return "DELETE FROM \"Enterprise\" WHERE id = " + id;
    }


    /**
     * Создание таблицы в базе данных
     * @param connection уже подключенный к базе данных
     */
    void CreateTable(Connection connection) throws SQLException
    {
        Statement statement;
        statement = connection.createStatement();

        String create = "CREATE TABLE \"Enterprise\" (\n" +
                "\t\"id\"\tinteger,\n" +
                "\t\"name\"\ttext,\n" +
                "\t\"banking_details\"\ttext,\n" +
                "\t\"contact_person\"\ttext,\n" +
                "\tPRIMARY KEY(\"id\")\n" +
                ");";
        statement.executeUpdate(create);
    }

    /**
     * отправляет все данные из листа в базу данных
     * @param connection коннект уже подключенный к базе данных
     * @param list лист который будет заноситься в базу
     */
    void send(Connection connection, ObservableList<Enterpise> list)
    {
        try
        {
            //Удаляем данные из таблицы
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP table \"Enterprise\"");
            CreateTable(connection);

//            preparedStatement = connection.prepareStatement("DELETE from \"Enterpise\"");
//            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        try
        {

//            for (Enterpise enterpise: list)
//            {
//                String query = "INSERT INTO \"Enterprise\"(id, name, banking_details, contact_person) " +
//                        "VALUES (?, ?, ?, ?)";
//
//                preparedStatement = connection.prepareStatement(query);
//
//                preparedStatement.setInt(1, enterpise.id);
//                preparedStatement.setString(2, enterpise.name);
//                preparedStatement.setString(3, enterpise.banking_details);
//                preparedStatement.setString(4, enterpise.contact_person);
//
//
//            }
//            preparedStatement.executeUpdate();

            //Создаем запрос на вставку(к сожалению он не безопасный)
            String query = "INSERT INTO \"Enterprise\"(id, name, banking_details, contact_person) " +
                        "VALUES";

            //Добавляем в строку данные, которые должны добавить
            for(Enterpise enterpise: list)
            {
                query += "(" + enterpise.getId() + "," +
                        "'" + enterpise.getName() + "'," +
                        "'" + enterpise.getBanking_details() + "'," +
                        "'" + enterpise.getContact_person() + "'), ";
            }

            //Убираем последнюю запятую и заменяем на ;
            query = query.substring(0, query.length() - 2);
            query+=";";

            //Делаем запрос
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);;
        }
    }


}
