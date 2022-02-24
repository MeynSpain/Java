import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

public class Main {



    public static void main(String[] args) throws IOException, InterruptedException {
        //город и токен
        String city = "Chita";
        String wheather_token ="7f24e73c03eac583deaa26a0e83df2c9";

        HttpClient client = HttpClient.newHttpClient(); //создали клиент, который отправляет запросы
        HttpRequest wheater_request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + wheather_token + "&units=metric"))
                .build();       //ССылка для запроса
/*
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()) //отправляем запрос
//                .thenApply(HttpResponse::body)      //получаем тело
//                .thenAccept(System.out::println)    //выводим данные в консоль
//                .join();                            //Соединяем
*/
        HttpResponse<String> response =
                client.send(wheater_request, HttpResponse.BodyHandlers.ofString()); //отправляем запрос и сохраняем в response

        Gson weat = new Gson();
        POP C = weat.fromJson(response.body(), POP.class);

        String str = response.body();   //получаем строку
        System.out.println("Температура: " + C.main.temp);


        ///Валюта

        String currency_from = "EUR";
        String currency_to = "RUB";
        String EUR = "EUR";
        String RUB = "RUB";
        String USD = "USD";
        String curr_token = "c3mKX9M7ghjr4mqpVWSxuKmhLbTzCd";


        HttpRequest curr_request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.amdoren.com/api/currency.php?api_key="+ curr_token + "&from=" + currency_from +"&to=" + currency_to + "&amount=1"))
                .build();

        HttpResponse<String> response_currency =
                client.send(curr_request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response_currency.body());

        Gson g = new Gson();
        Currency currency = g.fromJson(response_currency.body(), Currency.class);

        System.out.println("Валюта:" + currency.amount);

    }
    class Currency{
        public String error;
        public String error_message;
        public double amount;

    }


    class Wheater{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;

    }

    class Coord{
        public double lon;
        public double lat;
    }

    class POP{
        public Coord coord;
        public Wheater main;
    }
}
