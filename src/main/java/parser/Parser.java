package parser;

import command.*;
import exception.*;
import task.Deadline;
import task.Events;
import task.ToDos;

public class Parser {
    public static Command parse(String fullCommand) throws MiloException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].trim();

        switch (commandWord) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(parts[1].trim()));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(parts[1].trim()));
            case "delete":
                return new DeleteCommand(Integer.parseInt(parts[1].trim()));
            case "find":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TodoException();
                }
                return new FindCommand(parts[1].trim());
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TodoException();
                }
                return new AddCommand(new ToDos(parts[1].trim(), false));
            case "deadline": {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TodoException();
                }
                String[] arr = parts[1].split("/by", 2);
                String description = arr[0].trim();
                if (description.isEmpty()) throw new ByException(1);
                if(arr.length < 2) throw new ByException(2);
                String by = arr[1].trim();
                if (by.isEmpty()) throw new ByException(2);
                return new AddCommand(new Deadline(description, by, false));
            }
            case "event": {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new TodoException();
                }
                String[] arr1 = parts[1].split(" /from ", 2);
                if (arr1.length < 2) throw new EventException(2);
                String description = arr1[0].trim();
                String[] arr2 = arr1[1].split(" /to ", 2);
                if (arr2.length < 2) throw new EventException(3);
                String from = arr2[0].trim();
                String to = arr2[1].trim();
                if (description.isEmpty()) throw new EventException(1);
                if (from.isEmpty()) throw new EventException(2);
                if (to.isEmpty()) throw new EventException(3);
                return new AddCommand(new Events(description, from, to, false));
            }
            default:
                throw new UnknownTaskException();
        }
    }
}
