# Java
## Тут будут программы на java
По этой ссылке можно посмотреть обучающие видео по GUI Java  
https://www.youtube.com/watch?v=Kmgo00avvEw&t=3077s  
API  
https://gist.github.com/Da9el00/e8b1c2e5185e51413d9acea81056c2f9  
https://www.youtube.com/watch?v=zZoboXqsCNw

## Сайтик чтобы удалить фон
https://www.remove.bg/ru/upload  
## Немного о том как парсить сайты  
https://www.youtube.com/watch?v=sZgXUK5L3Ss

## jsoup maven  
https://mvnrepository.com/artifact/org.jsoup/jsoup/1.14.3  
## Online тест API  
https://reqbin.com/  
# Подключение по API  
''' sdad
        //город и токен
        String city = "Chita";
        String wheather_token ="7f24e73c03eac583deaa26a0e83df2c9";

        HttpClient client = HttpClient.newHttpClient(); //создали клиент, который отправляет запросы
        HttpRequest wheater_request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + wheather_token + "&units=metric"))
                .build();       //ССылка для запроса
                '''


```java
alert(s);
```
