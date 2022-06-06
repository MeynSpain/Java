public class Task {
    public static void main(String[] args) {
        //Простой разработчик
        Developer developer = new JavaDeveloper();
        System.out.println(developer.makeJob());

        //Повышаем разработчика до сеньора
        developer = new SeniorJavaDeveloper(developer);
        System.out.println(developer.makeJob());

        //Повышаем до тим лида
        developer = new JavaTeamLead(developer);
        System.out.println(developer.makeJob());

        //Создаем сразу тим лида, который умеет все
        Developer teamlead = new JavaTeamLead(new SeniorJavaDeveloper(new JavaDeveloper()));
        System.out.println(teamlead.makeJob());
    }
}
