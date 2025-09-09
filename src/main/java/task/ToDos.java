package task;

import task.Task;

public class ToDos extends Task {
    public ToDos(String command) {
        super(command);
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
