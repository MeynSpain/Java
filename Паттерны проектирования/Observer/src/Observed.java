/**
 * Сущность, которую будем наблюдать
 */
public interface Observed {


    /**
     * Добавить наблюдателя
     * @param observer Наблюдатель
     */
    public void addObserver(Observer observer);

    /**
     * Удалить наблюдателя
     * @param observer Наблюдатель
     */
    public void removeObserver(Observer observer);

    /**
     * Уведомить наблюдателей
     */
    public void notifyObservers();
}
