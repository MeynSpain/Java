import java.io.Serializable;
import java.util.Calendar;


/**
 * Простой класс только для хранения Даты, автора и текста сообщения
 */
public class DataMessage implements Serializable {

    String date;
    String author;
    String message;

    DataMessage(String date, String author, String message)
    {
        this.date = date;
        this.author = author;
        this.message = message;
    }
}


