public class DataBaseRunner {
    public static void main(String[] args) {
        //Создаем базу
        Database database = new Database();

        //Создаем разработчика, который будет работать с базой данных
        Developer developer = new Developer(
                new InsertCommand(database),
                new UpdateCommand(database),
                new SelectCommand(database),
                new DeleteCommand(database)
        );

        //Разработчик работает в базой
        developer.insertRecord();
        developer.updateRecord();
        developer.selectRecord();
        developer.deleteRecord();
    }
}
