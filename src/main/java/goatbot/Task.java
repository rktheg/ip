package goatbot;

/**
 * Represents one task in the task list.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description text that describes the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }
}
