import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Document getPage() throws IOException {               //будет возвращать документ(страницу)
        String url = "https://www.pogoda.spb.ru";    //вот по этому адресу
        Document page = Jsoup.parse(new URL(url), 3000);    //парсим страницу по адресу и возвращаем ее (url,время ожидания отета от страницы)
        return page;    //
    }

    // \d{2} - 2 символьных знака
    // \d{2}\.\d{2}     - нужный нам паттерн чтобы найти выражение вида 23.02
    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");   //патерн(шаблон) для поиска нужной информации в тексте

    //Pattern - шаблон,  Matcher - уже ишет по шаблону

    private static String getDateFromString(String stringDate) throws  Exception{    //вырежет только дату с помощью регулярных выражений
        //Матчер ищет нужные
        Matcher matcher = pattern.matcher(stringDate);  //ищем нужное выражение по шаблону
        if (matcher.find())     //если матчет что то нашел
        {
            return matcher.group(); //возвращаем то что нашел
        }
        else
        {
            throw new Exception("Can't extract date of string!");
        }
    }

    private static int printPartValues(Elements values, int index){

        int iterationCount = 4;

        if (index == 0){        //нынешний день
            //Проверяем что стоит в начале
            Element valueLn = values.get(0);

            if (valueLn.text().contains("Утро"))
            {
                iterationCount = 4;
            }
            if (valueLn.text().contains("День"))
            {
                iterationCount = 3;
            }
            if (valueLn.text().contains("Вечер"))
            {
                iterationCount = 2;
            }
            if (valueLn.text().contains("Ночь"))
            {
                iterationCount = 1;
            }

            /*
            Element valueLn = values.get(3);
            boolean isMorning = valueLn.text().contains("Утро");  //проверяем содержит ли текст слово Утро
            if (isMorning)
            {
                iterationCount = 3;
            }

             */
        }


        for (int i = 0; i < iterationCount; i++)
        {
            Element valueLine = values.get(index + i);
            for (Element td: valueLine.select("td")){
                System.out.print(td.text() + "    ");
            }
            System.out.println();
        }
        return iterationCount;
    }

    public static void main(String[] args) throws Exception{
        Document page = getPage();          //Получаем всю страницу
        //css query language
        Element tableWth = page.select("table[class=wt]").first();  //находим первый элемент с клаасом wt
        //System.out.println(tableWth);
        Elements names = tableWth.select("tr[class=wth]");    //выбираем только те блоки "tr" у которых class = wth (Если не понятно, посмотри код страницы!)
        Elements values = tableWth.select("tr[valign=top]");  //Получаем все tr у которых valign = top

        int index = 0;
        for (Element name : names)  //идем по всем датам, которые есть на сайте
        {
            String dateString = name.select("th[id=dt]").text();
            String date = getDateFromString(dateString);    //возвращаем только цифры
            System.out.println(date + "      Явления      Температура      Давление      Влажность      Ветер");
            int iterationCount = printPartValues(values, index);
            index += iterationCount;    //смещаем индекс

        }


    }
}
