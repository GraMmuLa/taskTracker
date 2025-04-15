package com.taskTracker.task;


import java.util.LinkedList;

public class TaskList extends LinkedList<Task> {
    @Override
    public boolean add(Task task) {
        return super.add(task);
    }

    public Task getTaskById(String id) {
        for(Task task : this) {
            if(task.getTaskId().toString().startsWith(id))
                return task;
        }
        return null;
    }
}