package goatbot.command;

import goatbot.task.Deadline;
import goatbot.task.Event;
import goatbot.task.Task;
import goatbot.task.Todo;

/**
 * Converts pipe-separated task records from storage into task objects.
 */
public class FileParser {
    private static final String DONE_STATUS = "1";

    /**
     * Parses a stored todo record.
     *
     * @param line pipe-separated todo record
     * @return todo represented by the record
     */
    public static Todo parseTodo(String line) {
        String[] parts = splitLine(line);
        Todo todo = new Todo(parts[2]);
        restoreStatus(todo, parts[1]);
        return todo;
    }

    /**
     * Parses a stored deadline record.
     *
     * @param line pipe-separated deadline record
     * @return deadline represented by the record
     */
    public static Deadline parseDeadline(String line) {
        String[] parts = splitLine(line);
        Deadline deadline = new Deadline(parts[2], parts[3]);
        restoreStatus(deadline, parts[1]);
        return deadline;
    }

    /**
     * Parses a stored event record.
     *
     * @param line pipe-separated event record
     * @return event represented by the record
     */
    public static Event parseEvent(String line) {
        String[] parts = splitLine(line);
        Event event = new Event(parts[2], parts[3], parts[4]);
        restoreStatus(event, parts[1]);
        return event;
    }

    private static String[] splitLine(String line) {
        return line.split("\\s*\\|\\s*", -1);
    }

    private static void restoreStatus(Task task, String status) {
        if (status.equals(DONE_STATUS)) {
            task.markAsDone();
        }
    }
}
