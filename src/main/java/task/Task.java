package task;

public class Task {
    public String taskDescription;
    public boolean isDone;
    public Task(String command, boolean isDone) {
        this.taskDescription = command;
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.taskDescription;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.taskDescription;
    }
}

