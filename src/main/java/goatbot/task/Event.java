package goatbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that happens from one specified time to another.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates an event task with the given description, start time, and end time.
     *
     * @param description text that describes the event
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DISPLAY_FORMAT)
                + " to: " + to.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }

    @Override
    public boolean occursOn(LocalDate date) {
        LocalDate startDate = from.toLocalDate();
        LocalDate endDate = to.toLocalDate();
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
