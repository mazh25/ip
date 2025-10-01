package storage;

import task.Task;
import task.ToDos;
import task.Deadline;
import task.Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                switch (type) {
                    case "T":
                        if (parts.length >= 3) {
                            tasks.add(new ToDos(parts[2], isDone));
                        }
                        break;
                    case "D":
                        if (parts.length >= 4) {
                            tasks.add(new Deadline(parts[2], parts[3], isDone));
                        }
                        break;
                    case "E":
                        if (parts.length >= 5) {
                            tasks.add(new Events(parts[2], parts[3], parts[4], isDone));
                        }
                        break;
                    default:
                        System.out.println("Warning: Unknown task type: " + type);
                }
            }
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            File dir = file.getParentFile();
            if (dir != null && !dir.exists()) dir.mkdirs();

            try (PrintWriter writer = new PrintWriter(file)) {
                for (Task task : tasks) {
                    writer.println(task.toSaveFormat());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: Failed to save tasks to file!");
        }
    }
}
