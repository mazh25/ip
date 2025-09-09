package exception;

public class EventException extends MiloException {
    public EventException(int type) {
        super(type == 1
                ? "Sorry! I cannot find the tasks to do by the time you give!"
                : type == 2 ? "Sorry! I do not know the start time to do the task!"
                            : "Sorry! I do not know the deadline to do this task!");
    }
}
