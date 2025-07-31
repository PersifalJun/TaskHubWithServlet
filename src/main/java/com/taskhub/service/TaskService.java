package com.taskhub.service;

import com.taskhub.dao.TaskDAO;

import com.taskhub.dto.TaskInfo;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.Task;
import com.taskhub.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getFixedCountTasks(int offset, int limit) {
        return taskDAO.getItems(offset,limit);
    }

    public Task getTaskById(Long id) {

        return taskDAO.getById(id).orElseThrow();

    }
    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }


    public void deleteTaskById(Long id) {
        taskDAO.deleteById(id);
    }


    public Task edit(Long taskId,TaskInfo info) {
        Task task = taskDAO.getById(taskId).orElseThrow(()->new RuntimeException("Not found"));
        task.setTitle(info.getTitle());
        task.setDescription(info.getDescription());
        task.setCompleted(info.isCompleted());
        task.setCreationDate(info.getCreationDate());
        task.setDeadline(info.getDeadline());
        return taskDAO.update(task);
    }

    public Task create(TaskInfo info) {
        Task task = new Task();
        task.setTitle(info.getTitle());
        task.setDescription(info.getDescription());
        task.setCompleted(info.isCompleted());
        task.setCreationDate(info.getCreationDate());
        task.setDeadline(info.getDeadline());
        return taskDAO.save(task);
    }

    public int getAllTasksCount() {
        return taskDAO.getAllCount();
    }
}
