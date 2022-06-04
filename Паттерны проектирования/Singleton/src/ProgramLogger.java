/**
 * Класс реализованнный по шаблону singleton
 */
public class ProgramLogger {

    //Создаем экземпляр класса
    private static ProgramLogger programLogger;

    //Просто переменная, которая будет отображать логи
    private static String logFile = "This is log file. \n\n";


    /**
     * synchronized добавлен для того, чтобы если этот класс используется в многопоточной среде,
     * т.е. если какой то второй поток обратится к методу, то он должен будет подождать, пока первый поток завершит
     * работу с методом.
     * @return Объект класса ProgramLogger
     */
    public static synchronized ProgramLogger getProgramLogger()
    {
        //Если память под объект еще не выделилась, то выделяем ее
        if (programLogger == null)
        {
            programLogger = new ProgramLogger();
        }
        //Если память уже выделена, то просто возвращаем объект
        //Это позволяет иметь всего один единственный экземпляр класса
        return programLogger;
    }


    /**
     * Приватный пустой конструктор, который вызовется всего один раз в методе getProgramLogger
     */
    private ProgramLogger(){
    }

    /**
     * Добавляет строку logInfo в историю
     * @param logInfo Добавляемая строка
     */
    public void addLogInfo(String logInfo)
    {
        logFile += logInfo + "\n";
    }

    /**
     * Вывод в консоли истории логов
     */
    public void showLogFile()
    {
        System.out.println(logFile);
    }

}
