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
            } else if (command.startsWith("todo")) {
                todoTask(command);
            } else if (command.startsWith("deadline")) {
                deadlineTask(command);
            } else if (command.startsWith("event")) {
                eventTask(command);
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
            System.out.println("  " + (i + 1) + "." + tasks[i].toString());
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

    private static void todoTask(String command) {
        String content = command.substring(5).trim();
        tasks[count++] = new ToDos(content);
        System.out.println("  ____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[count-1].toString());
        System.out.println(" Now you have "  + count + " tasks in the list.");
        System.out.println("  ____________________________________________________________");
    }

    private static void deadlineTask(String command) {
        String content = command.substring(9).trim();
        String[] parts = content.split("/by", 2);
        if (parts.length < 2) {
            System.out.println("  Please provide a /by date for the deadline!");
            return;
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks[count++] = new Deadline(description, by);
        System.out.println("  ____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[count - 1].toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("  ____________________________________________________________");
    }

    private static void eventTask(String command) {
        String content = command.substring(6).trim();
        String[] parts = content.split("/from", 2);
        if(parts.length < 2) {
            System.out.println("  Please provide a /from time for the event!");
            return;
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if(timeParts.length < 2) {
            System.out.println("  Please provide a /to time for the event!");
            return;
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        tasks[count++] = new Events(description, from, to);
        System.out.println("  ____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[count - 1].toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("  ____________________________________________________________");
    }
}

