public class JobSearch {
    public static void main(String[] args) {
        //Создаем сайт вакансий
        JobSite site = new JobSite();

        //Добавляем вакансии
        site.addVacancy("First Position");
        site.addVacancy("Second Position");


        //Создаем подписчиков
        Observer firstSub = new Subscriber("Sorokin A.E.");
        Observer secondSub = new Subscriber("Ivanov A.Y.");

        //Добавляем подписчиков на сайт
        site.addObserver(firstSub);
        site.addObserver(secondSub) ;

        //Теперь добавляем еще одну вакансию
        site.addVacancy("Third Position");

        //А теперь удалим вакансию
        site.removeVacancy("First Position");
    }
}
