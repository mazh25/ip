package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end date.
 */
public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event with a description, start date, end date, and done status.
     *
     * @param description the task description
     * @param fromStr     the start date as a string (yyyy-MM-dd)
     * @param toStr       the end date as a string (yyyy-MM-dd)
     * @param isDone      whether the task is done
     */
    public Events(String description, String fromStr, String toStr, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
        this.from = LocalDate.parse(fromStr);
        this.to = LocalDate.parse(toStr);
    }

    /**
     * Returns the start date of the event.
     *
     * @return start date
     */
    public LocalDate getFrom() { return from; }

    /**
     * Returns the end date of the event.
     *
     * @return end date
     */
    public LocalDate getTo() { return to; }

    /**
     * Returns a string representation of the event for display.
     *
     * @return formatted event string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns the event in a format suitable for saving to file.
     *
     * @return formatted string for storage
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + taskDescription + " | " + this.getFrom() + " | " + this.getTo();
    }
}
