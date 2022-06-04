package banking;

import Interfaces.Developer;
import Interfaces.ProjectManager;
import Interfaces.ProjectTeamFactory;
import Interfaces.Tester;

/**
 * Создаем сразу всю команду для разработки банковского приложения
 */
public class BankingTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new BankingPM();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }
}
