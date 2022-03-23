import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Internet {



    /**
     * Делает запрос на openweather и парсит полученный json файл и получает строку вида:
     * Температура: 10
     * Ощущается: 9
     * Максимум: 11
     * Минимум: 9
     * Давление: 1000
     * @return Строка с температурой
     */
    public String weather_info()        //Получаем строку с информацией о погоде
    {
        String info = getContent("https://api.openweathermap.org/data/2.5/weather?q=Chita&appid=7f24e73c03eac583deaa26a0e83df2c9&units=metric");

        JSONObject parse = new JSONObject(info);      //парсим полученный json файл

        info =   "Температура:" + parse.getJSONObject("main").getDouble("temp")
                +"\nОщущается:" + parse.getJSONObject("main").getDouble("feels_like")
                +"\nМаксимум:" + parse.getJSONObject("main").getDouble("temp_max")
                +"\nМинимум:" + parse.getJSONObject("main").getDouble("temp_min")
                +"\nДавление:" + parse.getJSONObject("main").getDouble("pressure");
        return info;
    }

    public String USD_info()
    {
        String info = getContent("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/usd.json");   //запрос на доллар
        JSONObject parse = new JSONObject(info);    //парсим полученный json файл
        info = "1 Доллар: " + parse.getJSONObject("usd").getDouble("rub") + " руб.";

        return info;
    }

    public String EUR_info()
    {
        String info = getContent("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur.json");   //запрос на доллар
        JSONObject parse = new JSONObject(info);    //парсим полученный json файл
        info = "1 Евро: " + parse.getJSONObject("eur").getDouble("rub") + " руб.";

        return info;
    }


    /**
     * Делает запрос по данному URL адресу и получает в ответ json файл
     * @param urlAddress URL адресс сайта на который отправляется запрос
     * @return  строка, в которой лежит json файл, который надо бы парсить
     */
    private String getContent(String urlAddress)    //Получаем json файл в строке по данному адресу
    {
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAddress);      //создаем url
            URLConnection connection = url.openConnection();     //открываем соединение

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));    //Получаем json файл

            String str;     //с помощью нее будем читать из reader-а

            while ((str = reader.readLine() ) != null)  //пока не дойдем до конца reader-а
            {
                content.append(str + "\n");
            }
            reader.close();         //закрываем



        }catch (Exception e){
            return "Что то пошло не так!";
        }

        return content.toString();

    }
}
