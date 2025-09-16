package ui;

import exception.*;
import task.*;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Milo {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        printLogo();
        run();
    }
    private static void initializeData() {
        File file = new File("./data/taskdata.txt");
        if(!file.exists()) {
            System.out.println("Data file not found. Starting with an empty task list.");
            return;
        }
        try(Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if(line.isEmpty()) continue;
                try {
                    Task task = parseLine(line);
                    if (task != null) tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Warning: ignoring malformed line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: taskdata.txt can not be found!");
        }

    }
    private static Task parseLine(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
        if(parts.length < 3) {
            System.out.println("Warning: line does not have enough parts!");
            return null;
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String taskDescription = parts[2];

        switch (taskType) {
        case "T":
            Task todo = new ToDos(taskDescription);
            todo.isDone = isDone;
            return todo;
        case "D":
            String by = parts.length >= 4 ? parts[3] : "";
            Task deadline = new Deadline(taskDescription, by);
            deadline.isDone = isDone;
            return deadline;
        case "E":
            String from = parts.length >= 4 ? parts[3] : "";
            String to = parts.length >= 5 ? parts[4] : "";
            Task event = new Events(taskDescription, from, to);
            event.isDone = isDone;
            return event;
        default:
            System.out.println("Warning: Unknown task type: " + taskType);
            return null;
        }
    }
    private static void saveToFile() {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if(!created) {
                    System.out.println("Warning: failed to create directory ./data");
                }
            }
            File file = new File(dir, "taskdata.txt");
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if(!created) {
                    System.out.println("Warning: failed to create taskdata.txt");
                }
            }

            try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {
                for (int i = 0; i < tasks.size(); i++) {
                    String line = getString(i);
                    writer.println(line);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: Failed to save tasks to file!");
        }
    }

    private static String getString(int i) {
        Task t = tasks.get(i);
        String line = "";
        if (t instanceof ToDos) {
            line = "T | " + (t.isDone ? "1" : "0") + " | " + t.taskDescription;
        } else if (t instanceof Deadline d) {
            line = "D | " + (t.isDone ? "1" : "0") + " | " + d.taskDescription + " | " + d.getBy();
        } else if (t instanceof Events e) {
            line = "E | " + (t.isDone ? "1" : "0") + " | " + e.taskDescription + " | " + e.getFrom() + " | " + e.getTo();
        }
        return line;
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
        saveToFile();
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
        saveToFile();
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
        saveToFile();
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
        saveToFile();
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
        saveToFile();
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
        saveToFile();
    }
}
