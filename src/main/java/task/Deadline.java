package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by; // 用 LocalDate 代替 String

    public Deadline(String description, String byStr, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
        this.by = LocalDate.parse(byStr); // 假设用户输入 yyyy-MM-dd
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + taskDescription + " | " + this.getBy();
    }
}
