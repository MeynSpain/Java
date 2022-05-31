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
private final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
        {  //Шаблоны для анализа
            // привет
            put("привет", "hello");
            put("здорово", "hello");
            put("здравствуй", "hello");

            put("табаки", "love");
            put("сказка", "love");
            put("люблю", "love");
            put("любишь", "love");

            put("наружность", "outside");
            put("наружности", "outside");
            put("прыгун", "outside");
            put("автобус", "outside");
            put("выпуск", "outside");

            put("стихи", "stihi");
            put("стих", "stihi");
            put("надписи", "stihi");
            put("горбач", "stihi");
            put("горбача", "stihi");

            put("новости", "novosti");
            put("новость", "novosti");
            put("новостей", "novosti");
            put("сводка", "novosti");
            put("сводку", "novosti");


            put("", "");

        }
    };
```

## ответы на шаблоны
```Java
private final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{    //Ответы по ключам
        put("hello", "Серый Дом стоит на окраине леса, в месте, называемом Расчёсками.\n" +
                "Куда же тебя забросит? К крысам, птицам, или в четвертую? Это решать точно\n" +
                "не тебе. Всё решит он - Дом.");
        put ("love", "Я не люблю истории. Я люблю мгновения. Люблю ночь больше утра, луну больше солнца,\n а здесь и сейчас, больше любого где-то потом.");
        put("outside", "Здесь нельзя говорить о наружности.");

    }};
```
## в мэйне
```Java
for (Map.Entry<String, String> entry : PATTERNS_FOR_ANALYSIS.entrySet())
        {

            pattern = Pattern.compile(entry.getKey());          //ищем нужный нам шаблон
            matcher = pattern.matcher(message);
//            if (pattern.matcher(message).find())                //если нашли такой шаблон
            if (matcher.find())                //если нашли такой шаблон
            {
                //Дата и время
                if (entry.getValue().equals("time"))        //Если запрос был на время
                {
                    say = getDate();
                    break;
                }
                if (entry.getValue().equals("stihi"))        //Если запрос был на время
                {
                    say = STIHI_GORBACHA[random.nextInt(STUPID_ANSWERS.length)];
                    break;
                }


                if (entry.getValue().equals("novosti"))        //Если запрос был на время
                {
                    say = NOVOSTI[random.nextInt(STUPID_ANSWERS.length)];
                    break;
                }
                say = ANSWERS_BY_PATTERNS.get(entry.getValue());   //и обращаемся по найденному к ответам
            }

        }
```
