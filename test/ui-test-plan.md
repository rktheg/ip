# UI Test Plan

Record text UI test cases here. Each test case should include its aim, console input, and exact expected
console output.

## Test Case: Say goodbye

Aim: Verify that Goat Bot starts, accepts the `bye` command, and prints the farewell message.

### Input

```text
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: List empty task list

Aim: Verify that the `list` command works before any tasks have been added.

### Input

```text
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
     Here are the tasks in your list:
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Add todo and list

Aim: Verify that a todo task is added and shown with the `[T]` type icon.

### Input

```text
todo borrow book
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added todo successfully, dont forget: 
    borrow book
    Now you have 1 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Add deadline and event

Aim: Verify that deadlines and events keep their date and time values as strings.

### Input

```text
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
deadline do homework /by no idea :-p
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added deadline successfully, DO ON TIME PLS: 
     return book
    Now you have 1 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
    added event successfully, better attend: 
    project meeting
    Now you have 2 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
    added deadline successfully, DO ON TIME PLS: 
     do homework
    Now you have 3 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[D][ ] return book (by: Sunday)
     2.[E][ ] project meeting (from: Mon 2pm to: 4pm)
     3.[D][ ] do homework (by: no idea :-p)
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Mark and unmark tasks

Aim: Verify that `mark` and `unmark` update task status icons correctly.

### Input

```text
todo read book
deadline return book /by Sunday
mark 1
unmark 1
mark 2
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added todo successfully, dont forget: 
    read book
    Now you have 1 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
    added deadline successfully, DO ON TIME PLS: 
     return book
    Now you have 2 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
[T][X] read book
    ____________________________________________________________
    ____________________________________________________________
     OK, I've marked this task as not done yet:
[T][ ] read book
    ____________________________________________________________
    ____________________________________________________________
     Nice! I've marked this task as done:
[D][X] return book (by: Sunday)
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][X] return book (by: Sunday)
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Reject unknown command

Aim: Verify that an unknown command shows the invalid input message and the program continues.

### Input

```text
hello
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

Invalid input. Please try again.
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Invalid command does not affect task list

Aim: Verify that an unknown command between valid commands does not add a task or prevent later commands from working.

### Input

```text
todo read book
hello
deadline return book /by Sunday
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added todo successfully, dont forget: 
    read book
    Now you have 1 tasks in your list.
    ____________________________________________________________
Invalid input. Please try again.
    ____________________________________________________________
    added deadline successfully, DO ON TIME PLS: 
     return book
    Now you have 2 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Sunday)
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Reject empty todo without changing task list

Aim: Verify that an empty todo command is rejected and does not create a blank task.

### Input

```text
todo borrow book
todo
todo    
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added todo successfully, dont forget: 
    borrow book
    Now you have 1 tasks in your list.
    ____________________________________________________________
Todo Format: 'todo xxx'
Todo Format: 'todo xxx'
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Reject invalid mark commands without changing states

Aim: Verify that invalid mark and unmark commands do not change existing task completion states.

### Input

```text
todo read book
todo write code
mark two
mark 3
unmark 0
mark 1
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added todo successfully, dont forget: 
    read book
    Now you have 1 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
    added todo successfully, dont forget: 
    write code
    Now you have 2 tasks in your list.
    ____________________________________________________________
Invalid input. Please try again.
Invalid input. Please try again.
Invalid input. Please try again.
    ____________________________________________________________
     Nice! I've marked this task as done:
[T][X] read book
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[T][ ] write code
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```

## Test Case: Reject malformed deadline and event without changing task list

Aim: Verify that incomplete deadline and event commands are rejected and do not add partial tasks.

### Input

```text
todo anchor task
deadline return book
deadline /by Sunday
event meeting /from 2pm
event /from 2pm /to 4pm
deadline submit report /by Friday
event project meeting /from Mon 2pm /to 4pm
list
bye
```

### Expected Output

```text
____________________________________________________________
 Hello! I'm Goat Bot.
 What can I do for you?
____________________________________________________________

    ____________________________________________________________
    added todo successfully, dont forget: 
    anchor task
    Now you have 1 tasks in your list.
    ____________________________________________________________
Invalid deadline format. Please try again.
Invalid deadline format. Please try again.
Invalid event format. Please try again.
Invalid event format. Please try again.
    ____________________________________________________________
    added deadline successfully, DO ON TIME PLS: 
     submit report
    Now you have 2 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
    added event successfully, better attend: 
    project meeting
    Now you have 3 tasks in your list.
    ____________________________________________________________
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] anchor task
     2.[D][ ] submit report (by: Friday)
     3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon! Happy hooping :)
____________________________________________________________
```
