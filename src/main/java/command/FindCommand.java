package command;

import storage.Storage;
import tasklist.TaskList;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.taskDescription.contains(keyword)) {
                matches.add(t);
            }
        }
        ui.showFindResult(matches);
    }
}
