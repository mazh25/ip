package exception;

public class TodoException extends MiloException{
    public TodoException() {
        super("Sorry! I can not find the things to do!!!");
    }
}
