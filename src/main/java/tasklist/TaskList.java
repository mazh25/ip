package tasklist;

import task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, access, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index the index of the task to delete (0-based).
     * @return the task that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index the index of the task to retrieve (0-based).
     * @return the task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
