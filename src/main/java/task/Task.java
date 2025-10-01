package task;

/**
 * Represents a task with a description and a done status.
 */
public class Task {
    public String taskDescription;
    public boolean isDone;

    /**
     * Constructs a Task with a description and its done status.
     *
     * @param command the task description
     * @param isDone  whether the task is done
     */
    public Task(String command, boolean isDone) {
        this.taskDescription = command;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task: "X" if done, " " if not.
     *
     * @return status icon as a string
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return formatted task string
     */
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.taskDescription;
    }

    /**
     * Sets the done status of the task.
     *
     * @param b new done status
     */
    public void setDone(boolean b) {
        this.isDone = b;
    }

    /**
     * Returns the task in a format suitable for saving to file.
     *
     * @return formatted string for storage
     */
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.taskDescription;
    }
}
