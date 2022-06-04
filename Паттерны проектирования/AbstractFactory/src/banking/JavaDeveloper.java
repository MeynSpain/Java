package banking;
import Interfaces.Developer;


public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer write Java code...");
    }
}
