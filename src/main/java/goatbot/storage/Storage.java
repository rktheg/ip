package goatbot.storage;

import goatbot.command.FileParser;
import goatbot.exception.GoatBotException;
import goatbot.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Saves and loads Goat Bot tasks on the hard disk.
 */
public class Storage {

    private final File file;

    /**
     * Creates a storage manager that writes to the specified file path.
     *
     * @param filePath path of the file used to store tasks
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Overwrites the storage file with the tasks currently in the task list.
     *
     * @param tasks array containing the tasks
     * @param taskCounter number of populated positions in the task array
     * @throws IOException if the directory or file cannot be written
     */
    public void saveTasks(Task[] tasks, int taskCounter) throws IOException {
        file.getParentFile().mkdirs();
        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < taskCounter; i++) {
                fw.write(tasks[i].toFileString());
                fw.write(System.lineSeparator());
            }
        }
    }

    /**
     * Loads stored tasks into the supplied task array.
     *
     * @param tasks array that receives the loaded tasks
     * @return number of tasks loaded
     * @throws IOException if the storage file cannot be read
     */
    public int loadTasks(Task[] tasks) throws IOException {
        if (!file.exists()) {
            return 0;
        }
        int taskCounter = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.startsWith("T")) {
                    tasks[taskCounter] = FileParser.parseTodo(line);
                } else if (line.startsWith("D")) {
                    tasks[taskCounter] = FileParser.parseDeadline(line);
                } else if (line.startsWith("E")) {
                    tasks[taskCounter] = FileParser.parseEvent(line);
                } else {
                    throw new GoatBotException("Invalid task entry in storage");
                }
                taskCounter++;
            }
        } catch (GoatBotException e) {
            System.out.println(e.getMessage());
        }
        return taskCounter;
    }
}
