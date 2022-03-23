import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static SimpleBot bot = new SimpleBot();

    public static void main(String[] args) throws ParseException {
//        new GUI_for_Bot();


        Scanner scanner = new Scanner(System.in);
        String quest = scanner.nextLine();
        System.out.println(bot.answer(quest));



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
