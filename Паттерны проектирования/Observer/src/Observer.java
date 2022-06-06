import java.util.List;

public interface Observer {
    /**
     * Обрабатывает событие
     * @param vacancies список вакансий
     */
    public void handleEvent(List<String> vacancies);
}
