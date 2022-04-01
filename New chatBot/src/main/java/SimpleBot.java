import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleBot {
    private final String[] STUPID_PHRASES = {   //Общие фразы
            "Нет ничего ценнее слов, сказанных к месту и ко времени.",
            "Порой молчание может сказать больше, нежели уйма слов.",
            "Перед тем как писать/говорить всегда лучше подумать.",
            "Вежливая и грамотная речь говорит о величии души.",
            "Приятно когда текст без орфографических ошибок.",
            "Многословие есть признак неупорядоченного ума.",
            "Слова могут ранить, но могут и исцелять.",
            "Записывая слова, мы удваиваем их силу.",
            "Кто ясно мыслит, тот ясно излагает.",
            "Боюсь Вы что-то не договариваете."};

    private final String[] STUPID_ANSWERS = {  //Ответы
            "Вопрос непростой, прошу тайм-аут на раздумья.",
            "Не уверен, что располагаю такой информацией.",
            "Может лучше поговорим о чём-то другом?",
            "Простите, но это очень личный вопрос.",
            "Не уверен, что Вам понравится ответ.",
            "Поверьте, я сам хотел бы это знать.",
            "Вы действительно хотите это знать?",
            "Уверен, Вы уже догадались сами.",
            "Зачем Вам такая информация?",
            "Давайте сохраним интригу?"};

    private final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{  //Шаблоны для анализа
        // привет
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // кто ты
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        // Как зовут
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        // Как дела
        put("как\\sоно", "howareyou");
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        //Что я делаю
        put("зачем\\s+я\\s.*тут", "whatdoidoing");
        put("зачем\\s+я\\s.*здесь", "whatdoidoing");
        put("что\\s+я\\s.*делаю", "whatdoidoing");
        put("чем\\s+я\\s.*занимаюсь", "whatdoidoing");

        // Что делаешь
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");


        // whatdoyoulike
        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        // iamfeelling
        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");
        // Согласие
        put("^да", "yes");
        put("согласен", "yes");
        // Дата и время
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // Прощание
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");

        //Интернет запросы
            //Погода
        put("как.*погода", "wheather");
        put("курс валют", "curs");

        //Простые мат операции
            //Умножение
        put("посчитай", "calculator");
        put("вычисли", "calculator");
        put("сколько\\s+будет", "calculator");

    }};

    private final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{    //Ответы по ключам
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Зовите меня Чаттер :)");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatdoyoudoing", "Я пробую общаться с людьми.");
        put("whatdoidoing", "Ну мне то откуда это знать, это ты должен спросить у себя самого.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
        put("yes", "Согласие есть продукт при полном непротивлении сторон.");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
    }};

    private Pattern pattern;    //шаблон для поиска
    private Random random;      //чтобы рандомить ответ, если не соответсвует шаблону
    private Calendar calendar;  //Для получения даты
    private Calculator calculator;
    private Matcher matcher;
    List<DataMessage> history_messages = new ArrayList<>();

    SimpleBot()
    {
        random = new Random();

    }

    /**
     * Получает текущую дату и время и преобразует
     * @return Строку даты и времени
     */
    private String getDate()
    {
        calendar = Calendar.getInstance();  //Получаем дату
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");   //создаем нужный шаблон
        String str = format.format(calendar.getTime());
        return str;//возвращаем отфарматированную дату
    }




    /**
     * Принимает на вход строку, которая обрабатывается и если есть
     * нужный паттерн, то возвращается соответствуется строка с ответом
     * @param msg сообщение которое передается боту, которое будет обрабатываться
     * @return String - ответ бота
     */
    public String answer(String msg, String author)
    {

        history_messages.add(new DataMessage(getDate(), author, msg));   //Добавляем в историю сообщение пользователя

        String say;
        if (msg.trim().endsWith("?"))       //удаляем начальные и конечные пробелы
        {
            say = STUPID_ANSWERS[random.nextInt(STUPID_ANSWERS.length)];    //рандомим ответ
        }
        else
        {
            say = STUPID_PHRASES[random.nextInt(STUPID_ANSWERS.length)];    //рандомим фразу
        }

        String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));   //переводим в нижний регистр и вырезаем все

        //Теперь пробежимся по мапам и поищем нужную нам фразу
        for (Map.Entry<String, String> entry: PATTERNS_FOR_ANALYSIS.entrySet())
        {
            pattern = Pattern.compile(entry.getKey());          //ищем нужный нам шаблон
            matcher = pattern.matcher(message);
//            if (pattern.matcher(message).find())                //если нашли такой шаблон
            if (matcher.find())                //если нашли такой шаблон
            {
                //Дата и время
                if (entry.getValue().equals("whattime"))        //Если запрос был на время
                {
                    say = getDate();                           //То возвращаем время
                    break;
                }
                //Погода
                if (entry.getValue().equals("wheather"))
                {
                    Internet weather = new Internet();
                    say = weather.weather_info();
                    break;
                }
                //Валюта
                if (entry.getValue().equals("curs"))
                {
                    Internet curs = new Internet();
                    say = curs.USD_info() + "\n" + curs.EUR_info();
                    break;
                }
                //Вычисления
                if (entry.getValue().equals("calculator"))
                {
                    calculator = new Calculator();



                        String buf = message.substring(matcher.end(), message.length());
                        try {
                            say = String.valueOf(calculator.result(buf));
                            break;
                        } catch (Exception e) {

                            e.printStackTrace();
                        }


                }



                say = ANSWERS_BY_PATTERNS.get(entry.getValue());   //и обращаемся по найденному к ответам
            }

        }

        history_messages.add(new DataMessage(getDate(), "Bot", say));
        return say;
    }


}
