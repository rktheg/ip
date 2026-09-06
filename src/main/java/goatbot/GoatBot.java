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
        Task[] tasks = new Task[MAX_TASK_COUNT]; //init task array with MAX_TASK_COUNT places

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(divider);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= taskCounter; i++) {
                    System.out.println("     " + i + "." + tasks[i - 1].toString());
                }
                System.out.println(divider);
            } else if (userInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(7));
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println(divider);
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskNumber - 1].toString());
                System.out.println(divider);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                tasks[taskNumber - 1].markAsDone();
                System.out.println(divider);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println(tasks[taskNumber - 1].toString());
                System.out.println(divider);
            } else if (userInput.startsWith("event ")) {
                int eventIndex = userInput.indexOf('/'); //find from
                String eventString = userInput.substring(6, eventIndex-1);
                String eventDate = userInput.substring(eventIndex+6);
                String[] eventDateSplit = eventDate.split("/to", 2);
                String eventStartTime = eventDateSplit[0].trim();
                String eventEndTime = eventDateSplit[1].trim();
                tasks[taskCounter++] = new Event(eventString, eventStartTime, eventEndTime);
                System.out.println(divider);
                System.out.println("    added event successfully, better attend: \n" +
                                   "    " + eventString);
                System.out.println("    Now you have " + taskCounter + " tasks in your list.");
                System.out.println(divider);
            } else if(userInput.startsWith("todo ")) {
                String todoString = userInput.substring(5);
                tasks[taskCounter++] = new Todo(todoString);
                System.out.println(divider);
                System.out.println("    added todo successfully, dont forget: \n" +
                                   "    " + todoString);
                System.out.println("    Now you have " + taskCounter + " tasks in your list.");
                System.out.println(divider);
            } else if (userInput.startsWith("deadline ")) {
                int deadlineIndex = userInput.indexOf("/");
                String deadlineString = userInput.substring(9, deadlineIndex-1); //to account for the additional space char as well
                String deadlineDate = userInput.substring(deadlineIndex+4); //+4 to account for /by
                tasks[taskCounter++] = new Deadline(deadlineString, deadlineDate);
                System.out.println(divider);
                System.out.println("    added deadline successfully, DO ON TIME PLS: \n" + " " +
                                   "    " + deadlineString);
                System.out.println("    Now you have " + taskCounter + " tasks in your list.");
                System.out.println(divider);
            } else {
                System.out.println("Invalid input. Please try again."); //guard
            }
            userInput = scanner.nextLine();
        }
        System.out.println(farewell);//if bye is typed
    }
}
