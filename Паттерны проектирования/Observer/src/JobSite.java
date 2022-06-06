import java.util.ArrayList;
import java.util.List;

/**
 * Сайт с вакансиями
 */
public class JobSite implements Observed{

    //Лист с вакансиями
    List<String> vacancies = new ArrayList<>();

    //Список подписчиков на сайт
    List<Observer> subscribers = new ArrayList<>();

    /**
     * Добавляет вкансию
     * @param vacancy
     */
    public void addVacancy(String vacancy)
    {
        //Добавляем вакансию
        vacancies.add(vacancy);

        //И уведомляем всех подписчиков
        notifyObservers();
    }

    /**
     * Удаление вакансии
     * @param vacancy
     */
    public void removeVacancy(String vacancy)
    {
        //Удаляем вакансию
        vacancies.remove(vacancy);

        //Уведомляем подписчиков
        notifyObservers();
    }

    /**
     * Добавляем подписчика, которого будем уведомлять
     * @param observer Наблюдатель (подписчик)
     */
    @Override
    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    /**
     * Удаляем подписчика
     * @param observer Наблюдатель
     */
    @Override
    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    /**
     * Уведомление подписчиков об изменениях
     */
    @Override
    public void notifyObservers()
    {
        //Проходим по всем подписчикам
        for (Observer observer: subscribers)
        {
            //И вызываем у них метод обработки события
            observer.handleEvent(vacancies);
        }
    }
}
