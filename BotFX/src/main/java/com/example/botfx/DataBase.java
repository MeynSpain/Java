package com.example.botfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DataBase{

    private final String DB_user_name = "postgres";

    private final String DB_password = "NYf3b&fh.d";

//    private final String DB_password = "maddyson228";

    private  final String DB_URL = "jdbc:postgresql://194.135.22.132:5432/chat";

    private Connection connection = null;

    private void DB_Connect() throws SQLException
    {
        //Коннектимся к базе данных
        connection = DriverManager.getConnection(DB_URL, DB_user_name, DB_password); //подключаемся к базе данных

    }



    /**
     * Берем историю сообщений бота и добавляем в базу данных
     * @param bot Объект Simple bot
     */
    public void DB_Insert(SimpleBot bot)
    {
        try {
            DB_Connect();   //Коннектимся к базе


            //объект который умеет отправлять запросы к БД
            String sql = "INSERT INTO history(date, author, message) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (DataMessage dataBuffer: bot.history_messages)
            {
                String date = dataBuffer.date;
                String author = dataBuffer.author;
                String message = dataBuffer.message;
                statement.setString(1, date);
                statement.setString(2, author);
                statement.setString(3, message);
                statement.executeUpdate();

            }
            statement.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
            e.printStackTrace();

        }

    }

    void DB_clear()
    {
        try {
            DB_Connect();

            Statement statement = connection.createStatement();

            statement.execute("DELETE FROM history");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Возвращает список истории сообщений
     *
     */
    List<DataMessage> DB_get_history_messages()
    {
        List<DataMessage> history = new ArrayList<>();
        try {
            DB_Connect();   //Коннектимся к базе

            //объект который умеет отправлять запросы к БД
            Statement statement = connection.createStatement();

            //Объект который хранит результат выполнения запроса
            ResultSet resultSet = statement.executeQuery("SELECT * FROM history");  //Возвращаем всю табличку

            //Идем по всем строка в таблице
            while (resultSet.next())
            {
                //Записываем данные из таблицы в буфер DataMessage
                DataMessage dataBuffer = new DataMessage(
                        resultSet.getString("date"),
                        resultSet.getString("author"),
                        resultSet.getString("message")
                );

                //А теперь добавляем этот объект в историю сообщений бота
               history.add(dataBuffer);
            }
            statement.close();
            resultSet.close();

            return history;
        }
        catch (SQLException e)  //ловим исключение, если не подключились к базе данных или что то другое пошло не так
        {
            System.out.println(e);
            e.printStackTrace();
        }
        return history;
    }

}
