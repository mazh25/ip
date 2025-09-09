package exception;

public class ByException extends MiloException {
    public ByException(int type) {
        super(type == 1
                ? "Sorry! I cannot find the tasks to do by the time you give!"
                : "Sorry! I do not know the deadline to do the task!");
    }
}
