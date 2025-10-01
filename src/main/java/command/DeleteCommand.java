package command;

import exception.IndexIllegalException;
import exception.MiloException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MiloException {
        int arrayIndex = index - 1;
        if (arrayIndex < 0 || arrayIndex >= tasks.size()) {
            throw new IndexIllegalException(index);
        }
        Task removed = tasks.deleteTask(arrayIndex);
        ui.showTaskDeleted(removed, tasks.size());
        storage.save(tasks.getTasks());
    }
}
