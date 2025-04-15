package com.taskTracker.task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;

public class TaskFile {

    private static final String fileName = "tasks.json";

    public static boolean existsFile()
    {
        return new File(fileName).exists();
    }

    private static boolean initFile()
    {
        File taskFile = new File(fileName);
        if(taskFile.exists())
            return false;
        try {
            return taskFile.createNewFile();
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean addElement(Task task)
    {
        initFile();
        JSONArray jsonArray = getFileJson().orElse(new JSONArray());
        jsonArray.add(task.toJSON());
        return saveChanges(jsonArray);
    }

    public static Optional<JSONArray> getFileJson()
    {
        if(existsFile())
        {
            try {
                StringBuilder jsonStr = new StringBuilder();
                for (String str : Files.readAllLines(new File(fileName).toPath())) {
                    jsonStr.append(str);
                }
                if(jsonStr.isEmpty())
                    return Optional.empty();

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(jsonStr.toString());

                return Optional.of(jsonArray);
            } catch (IOException | ParseException ex)
            {
                System.out.printf("Error: %s\n", ex.getMessage());
            }
        }
        return Optional.empty();
    }

    public static TaskList initTaskList()
    {
        TaskList taskList = new TaskList();
        if(existsFile())
        {
            JSONArray jsonArray = getFileJson().orElse(new JSONArray());
            if(jsonArray.isEmpty())
                return taskList;

            for(Object obj : jsonArray)
                taskList.add(Task.fromJSON((JSONObject)obj));
        }
        return taskList;
    }

    public static Optional<Object> getElementById(String id)
    {
        if(existsFile())
        {
            JSONArray jsonArray = getFileJson().orElseThrow();
            for(int i = 0; i < jsonArray.size(); i++)
            {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if(jsonObject.get("id").equals(id))
                    return Optional.of(jsonObject);
            }
        }
        return Optional.empty();
    }

    public static boolean updateElement(Task task)
    {
        if(existsFile())
        {
            JSONArray jsonArray = getFileJson().orElseThrow();
            Optional<Object> foundObject = getElementById(task.getTaskId().toString());

            foundObject.ifPresent(jsonArray::remove);
            jsonArray.add(task.toJSON());

            saveChanges(jsonArray);
        }
        return false;
    }

    public static boolean removeElement(Task task)
    {
        if(existsFile())
        {
            JSONArray jsonArray = getFileJson().orElseThrow();
            jsonArray.remove(task.toJSON());
            return saveChanges(jsonArray);
        }
        return false;
    }

    private static boolean saveChanges(JSONArray jsonArray)
    {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName)))
        {
            bufferedWriter.write(jsonArray.toJSONString());
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
