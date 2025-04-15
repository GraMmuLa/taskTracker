package com.taskTracker.commands;

import com.taskTracker.task.TaskFile;
import com.taskTracker.task.TaskList;

@picocli.CommandLine.Command(name = "task", mixinStandardHelpOptions = true, subcommands = {
        AddTaskCommand.class,
        PrintTasksCommand.class,
        RemoveTaskCommand.class,
        ChangeTaskStatusCommand.class
})
public class TaskCommand implements Runnable {

    private final TaskList taskList = TaskFile.initTaskList();

    @Override
    public void run() {
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
