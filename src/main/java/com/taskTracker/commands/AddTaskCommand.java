package com.taskTracker.commands;

import com.taskTracker.task.TaskFile;
import picocli.CommandLine;
import com.taskTracker.task.Task;

@CommandLine.Command(
        name = "add",
        sortOptions = false
)
public class AddTaskCommand implements Runnable {
    @CommandLine.ParentCommand
    private TaskCommand taskCommand;
    @CommandLine.Option(names = {"-t", "-task"}, required = true)
    private String taskName;

    @CommandLine.Option(names = {"-d", "--description"}, defaultValue = "No description")
    private String taskDescription;

    @Override
    public void run() {
        Task newTask = new Task(java.util.UUID.randomUUID(), taskName, taskDescription);
        taskCommand.getTaskList().add(newTask);
        TaskFile.addElement(newTask);
    }
}
