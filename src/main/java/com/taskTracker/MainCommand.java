package com.taskTracker;

import com.taskTracker.commands.TaskCommand;
import picocli.CommandLine;

@CommandLine.Command(name="main")
public class MainCommand implements Runnable {
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new MainCommand());
        commandLine.addSubcommand(new TaskCommand());
        commandLine.execute(args);
    }

    @Override
    public void run() {

    }
}
