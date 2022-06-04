package Projects;

import Interfaces.Developer;
import Interfaces.ProjectManager;
import Interfaces.ProjectTeamFactory;
import Interfaces.Tester;
import banking.BankingTeamFactory;
import website.PhpDeveloper;
import website.WebSiteTeamFactory;

public class Site {
    public static void main(String[] args) {
        //Создаем фабрику, для создания команды разработчиков
        ProjectTeamFactory teamFactory = new WebSiteTeamFactory();

        //Создаем команду
        Developer developer = teamFactory.getDeveloper();
        Tester tester = teamFactory.getTester();
        ProjectManager manager = teamFactory.getProjectManager();

        //И пусть теперь работают
        System.out.println("Creating website...\n");

        developer.writeCode();
        manager.manageProject();
        tester.testCode();

    }
}
