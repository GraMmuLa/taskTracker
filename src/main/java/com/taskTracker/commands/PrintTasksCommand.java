package com.taskTracker.commands;

import com.taskTracker.task.TaskStatus;
import picocli.CommandLine;
import com.taskTracker.task.Task;


@CommandLine.Command(name = "print-all")
public class PrintTasksCommand implements Runnable {
    @CommandLine.ParentCommand
    private TaskCommand taskCommand;

    @CommandLine.ArgGroup(exclusive = false)
    private FilterOptions filterOptions;

    static class FilterOptions
    {
        @CommandLine.Option(names={"-prg", "--in-progress"})
        private boolean onlyInProgress;

        @CommandLine.Option(names={"-d", "--d"})
        private boolean onlyDone;

        @CommandLine.Option(names={"-td", "--todo"})
        private boolean onlyTodo;
    }

    @Override
    public void run() {
            for (Task task : taskCommand.getTaskList()) {
                if(filterOptions == null ||
                        (filterOptions.onlyInProgress && task.getStatus().equals(TaskStatus.IN_PROGRESS)) ||
                        (filterOptions.onlyTodo && task.getStatus().equals(TaskStatus.TODO)) ||
                        (filterOptions.onlyDone && task.getStatus().equals(TaskStatus.DONE)))
                {
                    System.out.printf("%s. \n\tName: %s. \n\tDescription: %s. \n\tStatus: %s\n",
                            task.getTaskId(),
                            task.getTaskName(),
                            task.getTaskDescription(),
                            task.getStatus().name());
                }
        }
    }
}