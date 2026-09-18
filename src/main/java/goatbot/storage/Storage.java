package goatbot.storage;

import goatbot.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves Goat Bot tasks to a file on the hard disk.
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
}
