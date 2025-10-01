package ui;

import task.Task;
import tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Reads input from the user and displays messages, tasks, and errors.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new Ui object and initializes the input scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the user input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message and logo when the program starts.
     */
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

    /**
     * Displays a horizontal separator line.
     */
    public void showLine() {
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display.
     */
    public void showError(String message) {
        System.out.println("     " + message);
    }

    /**
     * Displays all tasks in the given TaskList.
     *
     * @param tasks the TaskList to display.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.getTask(i));
        }
        showLine();
    }

    /**
     * Displays a message after a task is added.
     *
     * @param task the task that was added.
     * @param size the total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message after a task is deleted.
     *
     * @param task the task that was removed.
     * @param size the total number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message after a task is marked as done.
     *
     * @param task the task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        showLine();
    }

    /**
     * Displays a message after a task is unmarked.
     *
     * @param task the task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        showLine();
    }

    /**
     * Displays the exit message when the program ends.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the results of a find/search operation.
     *
     * @param tasks a list of tasks matching the search keyword.
     */
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
