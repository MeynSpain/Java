package com.company;
import  java.util.*;
import  java.util.regex.*;

public class SimpleBot
{

    final String[] COMMON_PHRASES = {   //Общие фразы
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

    final String[] ELUSIVE_ANSWERS = {  //Ответы
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

    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{  //Шаблоны для анализа
        // привет
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // who
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        // name
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        // howareyou
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        // whatdoyoudoing
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
        // yes
        put("^да", "yes");
        put("согласен", "yes");
        // whattime
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // bye
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
    }};

    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{    //Ответы по ключам
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Зовите меня Чаттер :)");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatdoyoudoing", "Я пробую общаться с людьми.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
        put("yes", "Согласие есть продукт при полном непротивлении сторон.");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
    }};



    Pattern pattern;        //для Regex
    Random random;
    Date date;

    SimpleBot()
    {
        random = new Random();
        date = new Date();
    }

    public String sayInReturn(String msg, boolean ai)
    {
        String say = (msg.trim().endsWith("?"))?    //Заканчивается ли строка знаком вопроса
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:    //выдаем ответ если в конце стоял вопрос(рандомный)
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];      //выдаем общую фразу, если вопроса не было(рандомный)

        if (ai)     //если стоит галочка
        {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));    //берем строку, переводим в нижний регистр и убираем все знаки символы

            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet())    //Беребираем шаблоны для анализа
            {
                pattern = Pattern.compile(o.getKey());          //Смотрим ключи(левую часть), компилируем шаблон(сказали так необходимо в java)
                if (pattern.matcher(message).find())            //Ищем нужный нам ключ
                    if (o.getValue().equals("whattime"))
                        return date.toString();
                    else
                        return ANSWERS_BY_PATTERNS.get(o.getValue());  //получаем ответ
            }
        }
        return say;
    }
}
