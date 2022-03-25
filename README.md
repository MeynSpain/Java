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
## Ссылка с открытыми API  
https://github.com/public-apis/public-apis
# Подключение по API  
Создаем подключение к какому либо сайту, для данного сайта нужен токен(клю) для подключения.  
Его можно получить зарегестрировавшись на сайте в качестве разработчика вот по этой ссылочке  
https://openweathermap.org/api
```java
        //город и токен
        String city = "Chita";
        String wheather_token ="7f24e73c03eac583deaa26a0e83df2c9";

        HttpClient client = HttpClient.newHttpClient(); //создали клиент, который отправляет запросы
        HttpRequest wheater_request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + wheather_token + "&units=metric"))
                .build();       //ССылка для запроса
                '''
```
Далее мы должны отправить запрос на сайт и получить от туда json файл
```java
 HttpResponse<String> response =
                client.send(wheater_request, HttpResponse.BodyHandlers.ofString()); //отправляем запрос и сохраняем в response
```
В данном случае мы отправили и засунули содержимое в ``` HttpResponse<String> response ```, после чего дальше будем работать уже с файлом Json который лежит в переменной ``` response ```.  
Чтобы все это распарсить(разделить) и найти нужные нам данные используем библиотеку ```GSON```.  
Создадим 2 класса:
```java
 class Wheater{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;

    }
    
   class POP{
        public Coord coord;
        public Wheater main;
    }
```
POP - это класс общий который будет хранить в себе подклассы. Сейчас нам нужна погода. В файле Json нужная нам информация о температуре хранится в разделе который называется ```main``` поэтому создаем объект класса ```Wheater``` внутри класса ```POP```, ```coord``` там ширина и долгота, но это пока не важно.  
Вот собственно нужная нам часть в файле json:
```json
"main": {
        "temp": -7.24,
        "feels_like": -13.18,
        "temp_min": -7.24,
        "temp_max": -7.06,
        "pressure": 1011,
        "humidity": 53
    },
```
Теперь мы создаем объект класса ```GSON```, также создаем объекта класса ```POP``` (извините с фантазией туго, тем более ночью такое писать :( ), и берем из всего файла json только тело и пытаемся записать данные в класс ```POP```, поэтому было важно, чтобы поля класса назывались точно также как и в json, далее всю работу парсер делает сам, после чего выводим значение температуры на экран.
```java
        Gson weat = new Gson();
        POP C = weat.fromJson(response.body(), POP.class);
        System.out.println("Температура: " + C.main.temp);
```        
## Подгрузить бибиотеку GSON можно вот по этой ссылочке  
https://mvnrepository.com/artifact/com.google.code.gson/gson/2.9.0  
# Сериализация/десериализация объектов  
Сериализация используется для записи объектов в файл.  
У нас может быть класс большим количеством полей и имеется объект этого класса. Хотелось бы сохранить этот объект в файл, причем целиком, а не сохранять в текстовик каждое поле по отдельности преобразуя его в строку, учитывая то, что полями класса могут быть объекты другого класса. Для это и существует Сериализация. Мы просто берем наш объект и записываем его целиком в бинарный файл.  
Как же все таки это все сделать?  
Да вот так:
```java
public class User implements Serializable {
    int lifeLevel;
    Sword sword = new Sword();

    //Поля static и transient не сеарилизуются
    //transient поля после того как мы достанем объект из файла будут иметь значения по умолчанию
}
```   
У нас есть класс ```User``` и у него есть поля ```lifeLevel```, предположим, что это жизни персонажа, так же у него есть поле ```sword```, это поле как видно из названия отвечает за меч игрока, и как видно это объект другого класса
`java
public class Sword implements Serializable {
    int level;
    int damage;

}
` 
Который в свою очередь имеет поля ```level``` и ```damage```. Также видно что оба класса реализуют класс ```Serializable```. Вот как раз эта реализация и будет показывать, что эти 2 класса могут быть `Сериализованы`.   
Т.к. это обычный пример, то сделаем все простенько. И так, мы создали 2 класса и показали, что объекты этих классов могут быть сериализованы, простыми словами `записаны целиком в файл`. Теперь осталось создать объект и записать его, так чего же мы ждем?   
`java
        User user = new User();

        user.lifeLevel = 55;
        user.sword.damage = 100;
        user.sword.level = 10;

`

    
 


