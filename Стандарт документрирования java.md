# Javadoc
javadoc — это генератор документации в HTML-формате из комментариев исходного кода Java и определяет стандарт для документирования классов Java.
Для создания доклетов и тэглетов, которые позволяют программисту анализировать структуру Java-приложения, javadoc также предоставляет API.
В каждом случае комментарий должен находиться перед документируемым элементом.

При написании комментариев к кодам Java используют три типа комментариев :  

    // однострочный комментарий;
    /* многострочный комментарий */
    /** комментирование документации */
    
С помощью утилиты javadoc, входящей в состав JDK, комментарий документации можно извлекать и помещать в НТМL файл. Утилита javadoc позволяет вставлять HTML тэги и
использовать специальные ярлыки (дескрипторы) документирования. НТМL тэги заголовков не используют, чтобы не нарушать стиль файла, сформированного утилитой.

Дескрипторы javadoc, начинающиеся со знака @, называются автономными и должны помещаться с начала строки комментария (лидирующий символ * игнорируется).
Дескрипторы, начинающиеся с фигурной скобки, например {@code}, называются встроенными и могут применяться внутри описания.

Комментарии документации применяют для документирования классов, интерфейсов, полей (переменных), конструкторов и методов.
В каждом случае комментарий должен находиться перед документируемым элементом.

javadoc дескрипторы : @author, @version, @since, @see, @param, @return

Дескриптор | Применение                   | Описание |
---        | ---                          | ----     |
@author    |	Класс, интерфейс            |	Автор   |
@version	 | Класс, интерфейс	Версия      |Не более одного дескриптора на класс |
@since	   |Класс, интерфейс, поле, метод |	Указывает, с какой версии доступно  
@see	     |Класс, интерфейс, поле, метод	|Ссылка на другое место в документации
@param	   |Метод	                        |Входной параметр метода
@return	   |Метод	                        |Описание возвращаемого значения|
@exception имя_класса описание|	 Метод    |	Описание исключения, которое может быть послано из метода|
@throws имя_класса описание|	Метод	      |Описание исключения, которое может быть послано из метода|
@deprecated|Класс, интерфейс, поле, метод	|Описание устаревших блоков кода|
{@link reference}	|Класс, интерфейс, поле, метод	|Ссылка|
{@value}	 |Статичное поле	              |Описание значения переменной|

## Форма документирования кода  

Документирование класса, метода или переменной начинается с комбинации символов `/**` , после которого следует тело комментариев; заканчивается комбинацией символов `*/`.

В тело комментариев можно вставлять различные дескрипторы. Каждый дескриптор, начинающийся с символа `@` должен стоять первым в строке.
Несколько дескрипторов одного и того же типа необходимо группировать вместе. Встроенные дескрипторы (начинаются с фигурной скобки) можно помещать внутри любого
описания.
```Java
/** 
 * Класс продукции со свойствами <b>maker</b> и <b>price</b>.
 * @autor Киса Воробьянинов
 * @version 2.1
*/
class Product
{
    /** Поле производитель */
    private String maker;

    /** Поле цена */
    public double price;

    /** 
     * Конструктор - создание нового объекта
     * @see Product#Product(String, double)
     */
    Product()
    {
        setMaker("");
        price=0;
    }

    /** 
     * Конструктор - создание нового объекта с определенными значениями
     * @param maker - производитель
     * @param price - цена
     * @see Product#Product()
     */
    Product(String maker,double price){
        this.setMaker(maker);
        this.price=price;
    }

    /**
     * Функция получения значения поля {@link Product#maker}
     * @return возвращает название производителя
     */
    public String getMaker() {
        return maker;
    }

   /**
     * Процедура определения производителя {@link Product#maker}
     * @param maker - производитель
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }
}
```

Вот такие подсказки выдает IntelliJ IDEA:  
![image](https://user-images.githubusercontent.com/73224270/171822054-017f253d-92b2-432b-ae03-dd4df49cf46c.png)  
![image](https://user-images.githubusercontent.com/73224270/171822097-14239ee7-27a7-4d4a-ae57-d8508aea90f3.png)  
![image](https://user-images.githubusercontent.com/73224270/171822124-78182b83-19c9-4886-9d6b-413d06467d16.png)  
![image](https://user-images.githubusercontent.com/73224270/171822163-830d28bc-bb83-4d81-9718-8ca9ec6c3da7.png)  
![image](https://user-images.githubusercontent.com/73224270/171822196-be85ac3f-42de-47f4-95eb-923d62b288bb.png)  
![image](https://user-images.githubusercontent.com/73224270/171822265-28f91105-82b8-4c05-aba8-e4c6630b532d.png)  

# Источник
https://java-online.ru/java-javadoc.xhtml

