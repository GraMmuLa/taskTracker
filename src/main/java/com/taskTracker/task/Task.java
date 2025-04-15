package com.taskTracker.task;
import org.json.simple.JSONObject;

import java.util.UUID;

public class Task {
    private final UUID taskId;
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;

    public Task(UUID taskId, String taskName, String taskDescription) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = TaskStatus.TODO;
    }

    public Task(UUID taskId, String taskName, String taskDescription, TaskStatus taskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskStatus getStatus() {
        return taskStatus;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public JSONObject toJSON() {
        JSONObject taskJSON = new JSONObject();
        taskJSON.put("id", taskId.toString());
        taskJSON.put("name", taskName);
        taskJSON.put("description", taskDescription);
        taskJSON.put("status", taskStatus.toString());
        return taskJSON;
    }

    public static Task fromJSON(JSONObject taskJSON) {
        UUID taskId = UUID.fromString(taskJSON.get("id").toString());
        String taskName = taskJSON.get("name").toString();
        String taskDescription = taskJSON.get("description").toString();
        TaskStatus taskStatus = TaskStatus.valueOf(taskJSON.get("status").toString());
        return new Task(taskId, taskName, taskDescription, taskStatus);
    }
}