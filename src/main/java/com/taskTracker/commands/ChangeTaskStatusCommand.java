package com.taskTracker.commands;

import com.taskTracker.task.Task;
import com.taskTracker.task.TaskFile;
import com.taskTracker.task.TaskList;
import com.taskTracker.task.TaskStatus;
import picocli.CommandLine;

@CommandLine.Command(name="change-status")
public class ChangeTaskStatusCommand implements Runnable {
    @CommandLine.ParentCommand
    private TaskCommand taskCommand;

    @CommandLine.Option(names = {"--id", "-i"}, required = true)
    private String id;

    @CommandLine.ArgGroup(exclusive = true, multiplicity = "1")
    StatusOptions statusOptions;

    static class StatusOptions
    {
        @CommandLine.Option(names = {"--todo", "-td"}, required = false)
        private boolean isTodo;

        @CommandLine.Option(names = {"--done", "-d"}, required = false)
        private boolean isDone;

        @CommandLine.Option(names = {"--in-progress", "-prg"}, required = false)
        private boolean isInProgress;
    }

    @Override
    public void run() {
        TaskList taskList = taskCommand.getTaskList();
        Task taskToUpdate = taskList.getTaskById(id);
        taskList.remove(taskToUpdate);
        if(statusOptions.isTodo)
            taskToUpdate.setTaskStatus(TaskStatus.TODO);
        else if(statusOptions.isDone)
            taskToUpdate.setTaskStatus(TaskStatus.DONE);
        else if(statusOptions.isInProgress)
            taskToUpdate.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskList.add(taskToUpdate);
        TaskFile.updateElement(taskToUpdate);
    }
}
