package goatbot;

import java.util.Scanner;

/**
 * Entry point for Goat Bot.
 */
public class GoatBot {
    private static final int MAX_TASK_COUNT = 100;
    private static final String DIVIDER = "    ____________________________________________________________";

    /**
     * Starts the command loop and responds to user input.
     *
     * @param args command line arguments, currently unused
     */
    public static void main(String[] args) {
        String welcomeBanner = """
                ____________________________________________________________
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⢠⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣷⣾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⡿⢿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠋⠀⢈⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠠⠤⡾⠁⠀⢀⣿⣿⣿⣿⣿⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⡿⠿⣿⣿⣿⣿⣶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣠⣴⣾⡿⠟⠋⠁⠀⠀⠀⠈⠉⠛⠛⢿⣿⣦⣄⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⢀⣠⣾⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣷⣆⣀⠀⢀⡀
                ⢀⣤⣴⡿⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⠟⠋⠀
                ⠀⠉⠀⠀⠀⠀⠀
                 Hello! I'm Goat Bot.
                 What can I do for you?
                ____________________________________________________________
                """;

        String farewell = """
                ____________________________________________________________
                 Bye. Hope to see you again soon! Happy hooping :)
                ____________________________________________________________
                """;

        System.out.println(welcomeBanner);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int taskCounter = 0;
        Task[] tasks = new Task[MAX_TASK_COUNT]; //init task array with MAX_TASK_COUNT places

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                showList(tasks, taskCounter);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7));
                tasks[taskNumber - 1].markAsNotDone();
                showUnmarkedTask(tasks[taskNumber - 1]);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                tasks[taskNumber - 1].markAsDone();
                showMarkedTask(tasks[taskNumber - 1]);
            } else if (userInput.startsWith("event ")) {
                tasks[taskCounter] = parseEvent(userInput);
                taskCounter++;
                showAddedEvent(tasks[taskCounter - 1], taskCounter);
            } else if (userInput.startsWith("todo ")) {
                String todoString = userInput.substring(5);
                tasks[taskCounter++] = new Todo(todoString);
                showAddedTodo(tasks[taskCounter - 1], taskCounter);
            } else if (userInput.startsWith("deadline ")) {
                tasks[taskCounter] = parseDeadline(userInput);
                taskCounter++;
                showAddedDeadline(tasks[taskCounter - 1], taskCounter);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
            userInput = scanner.nextLine();
        }
        System.out.println(farewell);
    }

    private static Deadline parseDeadline(String userInput) {
        int deadlineIndex = userInput.indexOf("/");
        String deadlineString = userInput.substring(9, deadlineIndex - 1);
        String deadlineDate = userInput.substring(deadlineIndex + 4);
        return new Deadline(deadlineString, deadlineDate);
    }

    private static Event parseEvent(String userInput) {
        int eventIndex = userInput.indexOf('/');
        String eventString = userInput.substring(6, eventIndex - 1);
        String eventDate = userInput.substring(eventIndex + 6);
        String[] eventDateSplit = eventDate.split("/to", 2);
        String eventStartTime = eventDateSplit[0].trim();
        String eventEndTime = eventDateSplit[1].trim();
        return new Event(eventString, eventStartTime, eventEndTime);
    }

    private static void showList(Task[] tasks, int taskCounter) {
        System.out.println(DIVIDER);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= taskCounter; i++) {
            System.out.println("     " + i + "." + tasks[i - 1].toString());
        }
        System.out.println(DIVIDER);
    }

    private static void showMarkedTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(DIVIDER);
    }

    private static void showUnmarkedTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(DIVIDER);
    }

    private static void showAddedTodo(Task task, int taskCounter) {
        System.out.println(DIVIDER);
        System.out.println("    added todo successfully, dont forget: \n"
                + "    " + task.getDescription());
        System.out.println("    Now you have " + taskCounter + " tasks in your list.");
        System.out.println(DIVIDER);
    }

    private static void showAddedDeadline(Task task, int taskCounter) {
        System.out.println(DIVIDER);
        System.out.println("    added deadline successfully, DO ON TIME PLS: \n" + " "
                + "    " + task.getDescription());
        System.out.println("    Now you have " + taskCounter + " tasks in your list.");
        System.out.println(DIVIDER);
    }

    private static void showAddedEvent(Task task, int taskCounter) {
        System.out.println(DIVIDER);
        System.out.println("    added event successfully, better attend: \n"
                + "    " + task.getDescription());
        System.out.println("    Now you have " + taskCounter + " tasks in your list.");
        System.out.println(DIVIDER);
    }
}
