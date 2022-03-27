import java.sql.*;
import java.util.Calendar;

public class DataBase{

    private final String DB_user_name = "postgres";

    private final String DB_password = "maddyson228";

    private  final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/Chat";

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

            if (connection != null)
                System.out.println("Законнектился");
            //объект который умеет отправлять запросы к БД
            String sql = "INSERT INTO history(date, author, msg) VALUES (?, ?, ?)";
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
            e.printStackTrace();

        }

    }

    /**
     * Заполняет историю сообщений бота
     * @param bot Объект Simple bot для доступа к его истории сообщений
     */
    public void DB_get_history_messages(SimpleBot bot)
    {
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
                        resultSet.getString("msg")
                );

                //А теперь добавляем этот объект в историю сообщений бота
                bot.history_messages.add(dataBuffer);
            }
            statement.close();
            resultSet.close();
        }
        catch (SQLException e)  //ловим исключение, если не подключились к базе данных или что то другое пошло не так
        {
            e.printStackTrace();
        }
    }

}
