package com.taskTracker.commands;

import picocli.CommandLine;
import com.taskTracker.task.Task;


@CommandLine.Command(name = "print-all")
public class PrintTasksCommand implements Runnable {
    @CommandLine.ParentCommand
    private TaskCommand taskCommand;

    @Override
    public void run() {
        for (Task task : taskCommand.getTaskList()) {
            System.out.printf("%s. \n\tName: %s. \n\tDescription: %s. \n\tStatus: %s\n",
                    task.getTaskId(),
                    task.getTaskName(),
                    task.getTaskDescription(),
                    task.getStatus().name());
        }
    }
}