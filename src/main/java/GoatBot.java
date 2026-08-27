/**
 * Entry point for Goat Bot.
 */
import java.util.Scanner;
public class GoatBot {
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

        // string if user says bye
        String farewell = """
                ____________________________________________________________
                 Bye. Hope to see you again soon! Happy hooping :)
                ____________________________________________________________
                """;

        System.out.println(welcomeBanner);
        String divider = "    ____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int listCounter = 0;
        String[] listOfWords = new String[100];
        boolean[] isDone = new boolean[100];

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(divider);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= listCounter; i++) {
                    String statusIcon = isDone[i - 1] ? "[X]" : "[ ]";
                    System.out.println("     " + i + "." + statusIcon + " " + listOfWords[i - 1]);
                }
                System.out.println(divider);
            } else if (userInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                isDone[taskNumber - 1] = true;
                System.out.println(divider);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] " + listOfWords[taskNumber - 1]);
                System.out.println(divider);
            } else {
                listOfWords[listCounter++] = userInput;
                System.out.println(divider);
                System.out.println("     added: " + userInput);
                System.out.println(divider);
            }
            userInput = scanner.nextLine();
        }
        System.out.println(farewell);
    }
}
