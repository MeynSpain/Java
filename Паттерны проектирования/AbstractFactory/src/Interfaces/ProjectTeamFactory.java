package Interfaces;

/**
 * Интерфейс фабрики, которая сразу создает всю команду в числе
 * Разработчика, Менеджера и Тестера
 */
public interface ProjectTeamFactory {
    //Создает нового Разработчика
    Developer getDeveloper();
    //Создает нового Менеджера
    ProjectManager getProjectManager();
    //Создает нового Тестера
    Tester getTester();
}
