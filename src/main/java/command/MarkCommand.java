package command;

import exception.IndexIllegalException;
import exception.MiloException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MiloException {
        int arrayIndex = index - 1;
        if (arrayIndex < 0 || arrayIndex >= tasks.size()) {
            throw new IndexIllegalException(index);
        }
        tasks.getTask(arrayIndex).setDone(true);
        ui.showTaskMarked(tasks.getTask(arrayIndex));
        storage.save(tasks.getTasks());
    }
}

