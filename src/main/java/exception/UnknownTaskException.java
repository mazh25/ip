package exception;

public class UnknownTaskException extends MiloException{
    public UnknownTaskException() {
        super("I'm sorry, but I don't understand that command.");
    }
}
