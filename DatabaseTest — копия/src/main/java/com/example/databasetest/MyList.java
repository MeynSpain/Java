package com.example.databasetest;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyList {


    private static SqlQuery query = new SqlQuery(); //ПРосто для запросов

    public static void fillObservableList(ObservableList<Enterpise> list, Connection connection) throws SQLException
    {
        //Чистим лист
        list.clear();

        //Делаем запрос в базу и принимаем все в ResultSet
        ResultSet rs = connection.createStatement().executeQuery(query.getSelect());

        //Теперь просто все читаем и записываем в List
        //Пока что то есть, то считываем
        int i =0;
        while (rs.next())
        {
            list.add(new Enterpise
                    (
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("banking_details"),
                            rs.getString("contact_person")
                    ));
            i++;
        }
    }


}
