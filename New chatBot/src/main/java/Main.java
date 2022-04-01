import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static SimpleBot bot = new SimpleBot();

    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException, SQLException {

        String message = "123456";
        Pattern pattern = Pattern.compile("12");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            System.out.println("вошел");
//                        String buf = message.substring(pattern.matcher(message).end(), message.length() );
            String buf = message.substring(matcher.end(), message.length());
            System.out.println(buf);
        }
//        double a = 0.459999999994;
//        DecimalFormat decimalFormat = new DecimalFormat("#.####");
//        String result = decimalFormat.format(a);
//        a = Double.parseDouble(result);
//        System.out.println(result);
//        System.out.printf("%.3f\n", a);
//        System.out.println(a);


//        Scanner scanner = new Scanner(System.in);
//
//        String str = scanner.nextLine();
//
//        Calculator calculator = new Calculator();
//        try {
//            System.out.println(calculator.result(str));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



/*
        try {
            new GUI_for_Bot();
        }finally {
            //Записывае историю сообщений в файл
            FileOutputStream fileOutputStream = new FileOutputStream("History message.data");
            ObjectOutputStream write = new ObjectOutputStream(fileOutputStream);
            write.writeObject(bot.history_messages);
            write.close();
        }
 */


        Scanner scanner1 = new Scanner(System.in);

 //cos60+2*sin40+ln23


        for (int i = 0; i < 2; i++)
        {
            String quest = scanner1.nextLine();
            System.out.println(bot.answer(quest, "admin"));
        }

        DataBase dataBase = new DataBase();
        dataBase.DB_Insert(bot);

        SimpleBot new_bot = new SimpleBot();

        dataBase.DB_get_history_messages(new_bot);

        for (DataMessage dataMessage: new_bot.history_messages)
        {
            System.out.println(dataMessage.date +"//////" + dataMessage.author +"//////" + dataMessage.message);
        }





//        List<DataMessage> list_test = new ArrayList<>();

//        DataMessage dataMessage;


/*
        String URL = "jdbc:postgresql://127.0.0.1:5432/Chat";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }



        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(URL, "postgres", "maddyson228");

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }



        Statement st = connection.createStatement();


        st.execute("INSERT INTO \"history\" VALUES ('213213' , 'Ppp' , 'Hello')");

        /*
        PreparedStatement stt = connection.prepareStatement("INSERT INTO history VALUES (?, ?, ?)");
        stt.setString(1, "02.02.2002");
        stt.setString(2, "PAUL");
        stt.setString(3, "HEllo world");
        int res = stt.executeUpdate();

         */

/*
        ResultSet rs = st.executeQuery("SELECT * FROM history");
//        ResultSet rs = st.executeQuery("Show tables");



        while (rs.next())       //идем по строкам
        {
            System.out.print("Column 1 returned ");
            DataMessage dataMessage = new DataMessage(rs.getString(1), rs.getString(2), rs.getString(3));
//            System.out.println(rs.getString("date") + rs.getString("author") + rs.getString("msg"));
            System.out.println(dataMessage.date + dataMessage.author + dataMessage.message);
        }
        rs.close();
        st.close();

//        while ( (dataMessage = (DataMessage) read.readObject() ) != null)
//        {
////            test.add((DataMessage) read.readObject());
//            System.out.println("Попытался");
//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            System.out.println(format.format(dataMessage.date.getTime()) + " :: " + dataMessage.author + " :: " + dataMessage.message);
//
//        }
//        read.close();

//        for(DataMessage data: test)
//        {
//            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//            System.out.println(format.format(data.date.getTime()) + " :: " + data.author + " :: " + data.message);
//        }


  /*


        Scanner scanner = new Scanner(System.in);


       for (int i = 0; i < 5; i++)
       {
           String quest = scanner.nextLine();
           System.out.println(bot.answer(quest, "admin"));
       }
       for(DataMessage date: bot.history_messages)
       {
           SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

           System.out.println(format.format(date.date.getTime()) + " :: " + date.author + " :: " + date.message);
       }
        /*
        File file = new File("file.txt");

        if (!file.exists())
        {
            System.out.println("Вошел");
            file.createNewFile();
        }

        BufferedReader read  = new BufferedReader(new FileReader(file));
        //1 способ чтения
        /*1
        String line;
        while ( (line = read.readLine()) != null)
        {
            System.out.println(line);
        }

         */

        //2 способ чтения
        /*
        while (read.ready())//пока не конец файла
        {
            System.out.println(read.readLine());
        }

         */

//        FileOutputStream out = new FileOutputStream("test1.hui", true);    //открываем файл для записи в конец файла
//
//        PrintWriter write = new PrintWriter(out);       //передаем сюда этот файл для записи
//
//        write.println(dataMessage.date);
//        write.flush();
//        out.close();
//
//        FileInputStream in = new FileInputStream("test1");
//
//        Calendar t;
//        int str = in.read();
//        System.out.println((char)str);






//        Internet internet = new Internet();
//        System.out.println(internet.weather_info());
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar);

//        SimpleDateFormat format = new SimpleDateFormat("Текущее время и дата: HH:mm\n дата:dd-MM-yyyy ");
//        System.out.println(format.format(calendar.getTime()));
//        String str = format.format(calendar.getTime());
//        System.out.println(str);


//        System.out.println(date);


    }


}
