package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a specific due date.
 */
public class Deadline extends Task {

    private LocalDate by; // deadline date

    /**
     * Constructs a Deadline with a description, due date, and done status.
     *
     * @param description the task description
     * @param byStr       the due date as a string (yyyy-MM-dd)
     * @param isDone      whether the task is done
     */
    public Deadline(String description, String byStr, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
        this.by = LocalDate.parse(byStr);
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return due date
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline for display.
     *
     * @return formatted deadline string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the deadline in a format suitable for saving to file.
     *
     * @return formatted string for storage
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + taskDescription + " | " + this.getBy();
    }
}
