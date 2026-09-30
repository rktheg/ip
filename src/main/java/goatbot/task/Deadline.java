package goatbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specified time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private final LocalDateTime by;

    /**
     * Creates a deadline task with the given description and deadline.
     *
     * @param description text that describes the task
     * @param by deadline for the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by;
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return by.toLocalDate().equals(date);
    }
}
