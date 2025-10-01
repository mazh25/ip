package ui;

import task.Task;
import tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        String logo = """
                 __  __ _ _      \s
                |  \\/  (_) | ___ \s
                | |\\/| | | |/ _ \\\s
                | |  | | | |  __/\s
                |_|  |_|_|_|\\___|\s
                """;
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public void showLine() {
        System.out.println("  ____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("     " + message);
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.getTask(i));
        }
        showLine();
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        showLine();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showFindResult(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" No matching tasks found!");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        showLine();
    }

}
