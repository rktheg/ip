package goatbot;

import goatbot.command.Parser;
import goatbot.exception.GoatBotException;
import goatbot.storage.Storage;
import goatbot.task.Task;
import goatbot.ui.Ui;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Entry point for Goat Bot.
 */
public class GoatBot {
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please try again.";

    /**
     * Starts the command loop and responds to user input.
     *
     * @param args command line arguments, currently unused
     * @throws IOException if stored tasks cannot be loaded or saved
     */
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        ui.showWelcome();
        Storage storage = new Storage(
                Path.of("data", "goatbot.txt").toString()
        );
        ArrayList<Task> tasks = new ArrayList<>();
        storage.loadTasks(tasks);
        String userInput = ui.readCommand();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    ui.showList(tasks);
                } else if (userInput.startsWith("unmark ")) {
                    int taskNumber = parseTaskNumber(userInput, "unmark", tasks.size());
                    tasks.get(taskNumber - 1).markAsNotDone();
                    ui.showUnmarkedTask(tasks.get(taskNumber - 1));
                    storage.saveTasks(tasks);
                } else if (userInput.startsWith("mark ")) {
                    int taskNumber = parseTaskNumber(userInput, "mark", tasks.size());
                    tasks.get(taskNumber - 1).markAsDone();
                    ui.showMarkedTask(tasks.get(taskNumber - 1));
                    storage.saveTasks(tasks);
                } else if (userInput.startsWith("event ")) {
                    tasks.add(Parser.parseEvent(userInput));
                    ui.showAddedEvent(tasks.getLast(), tasks.size());
                    storage.saveTasks(tasks);
                } else if (userInput.equals("todo") || userInput.startsWith("todo ")) {
                    tasks.add(Parser.parseTodo(userInput));
                    ui.showAddedTodo(tasks.getLast(), tasks.size());
                    storage.saveTasks(tasks);
                } else if (userInput.startsWith("deadline ")) {
                    tasks.add(Parser.parseDeadline(userInput));
                    ui.showAddedDeadline(tasks.getLast(), tasks.size());
                    storage.saveTasks(tasks);
                } else if (userInput.startsWith("delete ")) {
                    int taskNumber = parseTaskNumber(userInput, "delete", tasks.size());
                    ui.showDeletedTask(tasks.get(taskNumber - 1), tasks.size() - 1);
                    tasks.remove(taskNumber - 1);
                    storage.saveTasks(tasks);
                } else {
                    ui.showError(INVALID_INPUT_MESSAGE);
                }
            } catch (GoatBotException | IOException e) {
                ui.showError(e.getMessage());
            }
            userInput = ui.readCommand();
        }
        ui.showGoodbye();
    }

    /**
     * Parses and validates the task number supplied with a task command.
     *
     * @param userInput complete command entered by the user
     * @param command command word preceding the task number
     * @param taskCounter number of tasks currently stored
     * @return valid one-based task number
     * @throws GoatBotException if the task number is missing, invalid, or out of range
     */
    private static int parseTaskNumber(String userInput, String command, int taskCounter) throws GoatBotException {
        try {
            int taskNumber = Integer.parseInt(userInput.substring(command.length()).trim());
            if (taskNumber < 1 || taskNumber > taskCounter) {
                throw new GoatBotException(INVALID_INPUT_MESSAGE);
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new GoatBotException(INVALID_INPUT_MESSAGE);
        }
    }
}
