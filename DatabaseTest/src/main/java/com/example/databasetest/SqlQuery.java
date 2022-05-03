package com.example.databasetest;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


}
