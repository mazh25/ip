import java.util.Scanner;

public class Milo {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int count = 0;

    public static void main(String[] args) {
        printLogo();
        run();
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim().toLowerCase();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printList();
            } else if (command.startsWith("mark")) {
                markTask(command);
            } else if (command.startsWith("unmark")) {
                unmarkTask(command);
            } else {
                addTask(command);
            }
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printLogo() {
        String logo = " __  __ _ _       \n"
                + "|  \\/  (_) | ___  \n"
                + "| |\\/| | | |/ _ \\ \n"
                + "| |  | | | |  __/ \n"
                + "|_|  |_|_|_|\\___| \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    private static void printList() {
        System.out.println("  ____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            String status = tasks[i].isDone ? "X" : " ";
            System.out.println("  " + (i + 1) + ".[" + status + "] " + tasks[i].taskDescription);
        }
        System.out.println("  ____________________________________________________________");
    }

    private static void markTask(String command) {
        String[] parts = command.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        tasks[index].isDone = true;
        System.out.println("  ____________________________________________________________");
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("    [X] " + tasks[index].taskDescription);
        System.out.println("  ____________________________________________________________");
    }

    private static void unmarkTask(String command) {
        String[] parts = command.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        tasks[index].isDone = false;
        System.out.println("  ____________________________________________________________");
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println("    [ ] " + tasks[index].taskDescription);
        System.out.println("  ____________________________________________________________");
    }

    private static void addTask(String command) {
        tasks[count++] = new Task(command);
        System.out.println("  ____________________________________________________________");
        System.out.println("  added: " + command);
        System.out.println("  ____________________________________________________________");
    }
}

