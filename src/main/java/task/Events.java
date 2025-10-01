package task;

public class Events extends Task {
    protected String from;
    protected String to;
    public Events(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return this.from;
    }
    public String getTo() {
        return this.to;
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + taskDescription + " | " + from + " | " + to;
    }
}
