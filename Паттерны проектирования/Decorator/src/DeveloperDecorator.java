/**
 * Декоратор, который в конструкторе будет принимать разработчика, и у него будет метод makeJob
 */
public class DeveloperDecorator implements Developer{
    Developer developer;

    public DeveloperDecorator(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String makeJob() {
        return developer.makeJob();
    }
}
