# Тип данных словарь
Словарь в Java реализуется с помощью интерфейса Map. Мы разберем его реализацию классом HashMap. 
Словарь (dictionary) — это структура данных, позволяющая хранить информацию в виде комбинации «ключ» — «значение» (key — value). То есть, с каким либо значением (value) мы ассоциируем ключ (это может быть: id, термин и т. д.) и эту пару заносим в словарь.

# Действия со словарём
Описание:
```Java
Map<String,String> dictionary = new HashMap<String,String>();
```

Добавление элемента в словарь:
```Java
dictionary.put(key, value);
```
Получение значения элемента по ключу:
```Java
dictionary.get(key);
```
Удаление записи с определенным ключом:
```Java
dictionary.remove(key);
```
Поиск элемента словаря по ключу. Если содержится - true, иначе false
```Java
dictionary.containsKey(key);
```
Получить все ключевые слова всех элементов словаря:
```Java
dictionary.keySet();
```
Получить все значения элементов ассоциативного массива:
```Java
dictionary.values();
```
Получить количество записей в словаре:
```Java
dictionary.size();
```
Вернуть все пары "ключ-значение" из данного словаря:
```Java
dictionary.toString();
```

# На примере чат-бота
## шаблоны для проверки
```Java
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

        //Спасибо
        put("пасибо", "thanks");
        put("пасибки", "thanks");
        put("благодарю", "thanks");
        put("благодарен", "thanks");
        put("от души", "thanks");


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
        put("пока", "bye");

        //

        //Интернет запросы
            //Погода
        put("как.*погода", "wheather");
        put("курс валют", "curs");
            //Запросы в браузер
        put("найди", "search");
        put("найти", "search");
        put("поиск", "search");
        put("поищи", "search");

        //Простые мат операции
            //Умножение
        put("посчитай", "calculator");
        put("вычисли", "calculator");
        put("сколько\\s+будет", "calculator");

    }};
```

## ответы на шаблоны
```Java
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
        put("thanks", "Да не за что, обращайся.");
    }};
```
## в мэйне
```Java
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
                //Поисковый запрос
                if (entry.getValue().equals("search"))
                {
                    //Вырезаем сам поисковый запрос
                    String request = message.substring(matcher.end(), message.length());

                    Internet search = new Internet();

                    try {
                        search.search(request);
                        say = "Вот что удалось найти";

                        break;
                    } catch (URISyntaxException e) {
                        say = "Не получилось найти, прошу прощения :(";
                        break;
                    } catch (IOException e) {
                        say = "Что то пошло не так, сорян :(";
                        break;
                    }
                }



                say = ANSWERS_BY_PATTERNS.get(entry.getValue());   //и обращаемся по найденному к ответам
            }

        }
```
