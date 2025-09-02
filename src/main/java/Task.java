public class Task {
    public String taskDescription;
    public boolean isDone;
    public Task(String command) {
        this.taskDescription = command;
        isDone = false;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    public void setIsDone() {
        this.isDone = true;
    }
}
