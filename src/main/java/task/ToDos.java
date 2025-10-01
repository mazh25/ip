package task;

import task.Task;

public class ToDos extends Task {
    public ToDos(String command, boolean isDone) {
        super(command, isDone);
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
