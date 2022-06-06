/**
 * Класс, который является фасадом, скрывающим все тонкости работы других классов
 */
public class Workflow {

    private Developer developer = new Developer();

    private Job job =  new Job();

    private BugTracker bugTracker = new BugTracker();

    /**
     * Запускает работу
     */
    public void solveProblems()
    {
        job.doJob();
        bugTracker.startSprint();
        developer.doJobBeforeDeadline(bugTracker);
    }

}
