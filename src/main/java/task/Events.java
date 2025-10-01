package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    public Events(String description, String fromStr, String toStr, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
        this.from = LocalDate.parse(fromStr);
        this.to = LocalDate.parse(toStr);
    }

    public LocalDate getFrom() { return from; }
    public LocalDate getTo() { return to; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + taskDescription + " | " + this.getFrom() + " | " + this.getTo();
    }
}
