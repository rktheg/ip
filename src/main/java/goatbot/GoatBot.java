package goatbot;

import java.util.Scanner;

/**
 * Entry point for Goat Bot.
 */
public class GoatBot {
    private static final int MAX_TASK_COUNT = 100;

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
        String divider = "    ____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int taskCounter = 0;
        Task[] tasks = new Task[MAX_TASK_COUNT];

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(divider);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= taskCounter; i++) {
                    String statusIcon = tasks[i - 1].getStatusIcon();
                    System.out.println("     " + i + ".[" + statusIcon + "] "
                            + tasks[i - 1].getDescription());
                }
                System.out.println(divider);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7));
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println(divider);
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [ ] " + tasks[taskNumber - 1].getDescription());
                System.out.println(divider);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                tasks[taskNumber - 1].markAsDone();
                System.out.println(divider);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] " + tasks[taskNumber - 1].getDescription());
                System.out.println(divider);
            } else {
                tasks[taskCounter++] = new Task(userInput);
                System.out.println(divider);
                System.out.println("     added: " + userInput);
                System.out.println(divider);
            }
            userInput = scanner.nextLine();
        }
        System.out.println(farewell);
    }
}
