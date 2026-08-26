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
        System.out.println(welcomeBanner);
        Scanner scanner = new Scanner(System.in); //scanner object attached to stdin like keyboard
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(
                    "\n____________________________________________________________\n" +
                            userInput + "\n" +
                    "____________________________________________________________");
            userInput = scanner.nextLine();
        }
        // string if user says bye
        String farewell = """
                ____________________________________________________________
                Bye. Hope to see you again soon! Happy hooping :)
                ____________________________________________________________
                """;
        System.out.println(farewell);
    }
}
