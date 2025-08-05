package com.taskhub.service;

import com.taskhub.dao.TaskDAO;

import com.taskhub.dto.TaskInfo;

import com.taskhub.entity.Task;


public class TaskService extends AbstractService<Task> {

    public TaskService(TaskDAO taskDAO) {
      super(taskDAO);
    }



    public Task edit(Long taskId,TaskInfo info) {

        Task task = dao.getById(taskId).orElseThrow(()->new RuntimeException("Not found"));
        task.setTitle(info.getTitle());
        task.setDescription(info.getDescription());
        task.setCompleted(info.isCompleted());
        task.setCreationDate(info.getCreationDate());
        task.setDeadline(info.getDeadline());
        return dao.update(task);
    }

    public Task create(TaskInfo info) {

        Task task = new Task();
        task.setTitle(info.getTitle());
        task.setDescription(info.getDescription());
        task.setCompleted(info.isCompleted());
        task.setCreationDate(info.getCreationDate());
        task.setDeadline(info.getDeadline());
        return dao.save(task);
    }

}
