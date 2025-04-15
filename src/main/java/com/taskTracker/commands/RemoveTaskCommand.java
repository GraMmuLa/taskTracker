package com.taskTracker.commands;

import com.taskTracker.task.Task;
import com.taskTracker.task.TaskFile;
import picocli.CommandLine;
import com.taskTracker.task.TaskList;

@CommandLine.Command(
        name = "remove",
        sortOptions = false
)
public class RemoveTaskCommand implements Runnable {
    @CommandLine.ParentCommand
    private TaskCommand taskCommand;

    @CommandLine.Option(names = {"-i", "--id"}, required = true)
    private String taskId;

    @Override
    public void run() {
        TaskList taskList = taskCommand.getTaskList();
        Task taskToDelete = taskList.getTaskById(taskId);
        taskList.remove(taskToDelete);
        TaskFile.removeElement(taskToDelete);
    }
}