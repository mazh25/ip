package ui;

import exception.*;
import task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Milo {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printLogo();
        run();
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim().toLowerCase();

        while (!command.equals("bye")) {
            try {
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
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                } else {
                    throw new UnknownTaskException();
                }
            } catch (MiloException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            }

            command = sc.nextLine().trim().toLowerCase();
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
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("  ____________________________________________________________");
    }

    private static void markTask(String command) throws MiloException {
        String[] parts = command.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if(index >= tasks.size() || index < 0) {
            throw new IndexIllegalException(index);
        }
        tasks.get(index).isDone = true;
        System.out.println("  ____________________________________________________________");
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("    " + tasks.get(index));
        System.out.println("  ____________________________________________________________");
    }

    private static void unmarkTask(String command) throws MiloException {
        String[] parts = command.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if(index >= tasks.size() || index < 0) {
            throw new IndexIllegalException(index);
        }
        tasks.get(index).isDone = false;
        System.out.println("  ____________________________________________________________");
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println("    " + tasks.get(index));
        System.out.println("  ____________________________________________________________");
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("  ____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("  ____________________________________________________________");
    }

    private static void todoTask(String command) throws MiloException {
        if(command.length() < 5) {
            throw new TodoException();
        }
        String content = command.substring(5).trim();
        addTask(new ToDos(content));
    }

    private static void deadlineTask(String command) throws MiloException {
        if(command.length() < 9) {
            throw new TodoException();
        }
        String content = command.substring(9).trim();
        String[] parts = content.split("/by", 2);
        if (parts.length < 2) {
            throw new ByException(2);
        }
        if(parts[0].isEmpty()) throw new ByException(1);
        if(parts[1].isEmpty()) throw new ByException(2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        addTask(new Deadline(description, by));
    }

    private static void eventTask(String command) throws MiloException {
        if(command.length() < 6) {
            throw new TodoException();
        }
        String content = command.substring(6).trim();
        String[] parts = content.split("/from", 2);
        if (parts.length < 2) {
            throw new EventException(2);
        }
        String description = parts[0].trim();
        if(description.isEmpty()) throw new EventException(1);
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new EventException(3);
        }
        String from = timeParts[0].trim();
        if(from.isEmpty()) throw new EventException(2);
        String to = timeParts[1].trim();
        if(to.isEmpty()) throw new EventException(3);
        addTask(new Events(description, from, to));
    }

    private static void deleteTask(String command) throws MiloException {
        String[] parts = command.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if(index >= tasks.size() || index < 0) {
            throw new IndexIllegalException(index);
        }
        Task removed = tasks.remove(index);
        System.out.println("  ____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removed);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("  ____________________________________________________________");
    }
}
