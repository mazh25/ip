# Milo Chatbot User Guide

## Introduction
**Milo** is a personal task management chatbot that helps you organize your tasks efficiently. It runs in the console and stores your tasks locally in a file. Milo supports adding, deleting, marking tasks as done, searching tasks, and listing all tasks in a user-friendly interface.

---

## Features
- **Add Tasks**
  - **ToDo:** simple task without a date.
  - **Deadline:** task with a due date.
  - **Event:** task with start and end dates.
- **Delete Tasks**
- **Mark / Unmark Tasks**
- **List Tasks**
- **Search Tasks**
- **Exit Application**

---

## Getting Started

### Running Milo
Run the program from the command line:
```bash
java Milo
````

Milo will display a welcome message and prompt you for commands.

---

## Task Commands

### 1. Adding Tasks

#### ToDo

```
todo <description>
```

Example:

```
todo Buy groceries
```

#### Deadline

```
deadline <description> /by <yyyy-MM-dd>
```

Example:

```
deadline Submit report /by 2025-10-10
```

#### Event

```
event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>
```

Example:

```
event Project meeting /from 2025-10-05 /to 2025-10-06
```

Milo confirms the task has been added and shows the total number of tasks.

---

### 2. Deleting a Task

```
delete <task number>
```

Example:

```
delete 2
```

Milo removes the task and displays the updated task count.

---

### 3. Marking / Unmarking Tasks

#### Mark as done

```
mark <task number>
```

Example:

```
mark 1
```

#### Unmark (mark as not done)

```
unmark <task number>
```

Example:

```
unmark 1
```

Milo displays a confirmation message after the operation.

---

### 4. Listing All Tasks

```
list
```

Milo displays all tasks with their type, status, and relevant dates:

* `[T]` ToDo
* `[D]` Deadline
* `[E]` Event
* `[X]` done / `[ ]` not done

**Example Task List Display:**

```
1.[T][ ] Buy groceries
2.[D][X] Submit report (by: Oct 10 2025)
3.[E][ ] Project meeting (from: Oct 5 2025 to: Oct 6 2025)
```

---

### 5. Searching Tasks

```
find <keyword>
```

Example:

```
find report
```

Milo displays all tasks containing the keyword.

---

### 6. Exiting Milo

```
bye
```

Milo displays a goodbye message and exits.

---

## Input Guidelines

* Dates must be in the format `yyyy-MM-dd`.
* Task numbers must be valid (within the current list range).
* Commands must follow the exact formats shown above.

---

## Error Handling

Milo provides user-friendly error messages for invalid inputs:

* **UnknownTaskException**: Command not recognized.
* **TodoException**: ToDo command missing description.
* **ByException**: Deadline command missing or invalid date.
* **EventException**: Event command missing description, start date, or end date.
* **IndexIllegalException**: Task number is out of range.

Example error message:

```
Sorry! I can not find the things to do!!!
```

---

## Data Persistence

* Tasks are stored in `data/tasks.txt`.
* Milo loads existing tasks on startup.
* Changes are saved automatically when tasks are added, deleted, or updated.

---

## Quick Reference Table

| Command      | Format                                 | Example                                         |
| ------------ | -------------------------------------- | ----------------------------------------------- |
| Add ToDo     | `todo <description>`                   | `todo Buy groceries`                            |
| Add Deadline | `deadline <description> /by <date>`    | `deadline Submit report /by 2025-10-10`         |
| Add Event    | `event <desc> /from <start> /to <end>` | `event Meeting /from 2025-10-05 /to 2025-10-06` |
| Delete       | `delete <task number>`                 | `delete 2`                                      |
| Mark Done    | `mark <task number>`                   | `mark 1`                                        |
| Unmark       | `unmark <task number>`                 | `unmark 1`                                      |
| List         | `list`                                 | `list`                                          |
| Find         | `find <keyword>`                       | `find report`                                   |
| Exit         | `bye`                                  | `bye`                                           |

---

## Tips

* Keep descriptions concise for easier readability.
* Always check task numbers before marking or deleting.
* Milo automatically saves your work—no need to manually save.

---

Enjoy managing your tasks efficiently with **Milo**!


