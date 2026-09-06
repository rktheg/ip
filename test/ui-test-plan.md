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
