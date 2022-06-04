
public class Program {
    public static void main(String[] args) {
        //Создаем фабрику, которая создаст разработчика указанного разработчика
        DeveloperFactory developerFactory = createDeveloperBySpeciality("php");

        //Теперь из фабрики создаем разработчика
        Developer developer = developerFactory.createDeveloper();

        //Вызываем метод писать код
        developer.writeCode();
    }


    /**
     *  Создает разработчика по указанной специальности
     *  @param speciality Специальность, по которой будет создан разработчик
     *  @return новый разработчик
     */
    static DeveloperFactory createDeveloperBySpeciality(String speciality)
    {
        //Проверяем на совпадения специальности
        if (speciality.equalsIgnoreCase("java"))
        {
            return new JavaDeveloperFactory();
        }
        else if (speciality.equalsIgnoreCase("c++"))
        {
            return new CppDeveloperFactory();
        }
        else if (speciality.equalsIgnoreCase("php"))
        {
            return new PhpDeveloperFactory();
        }
        else
        {
            throw new RuntimeException(speciality + " is unknown speciality.");
        }
    }
}
