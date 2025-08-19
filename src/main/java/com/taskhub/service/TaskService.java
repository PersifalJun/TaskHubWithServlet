package com.taskhub.service;

import com.taskhub.dao.DAO;
import com.taskhub.dao.ProjectDAO;
import com.taskhub.dao.TaskDAO;

import com.taskhub.dao.TaskListDAO;
import com.taskhub.dto.TaskInfo;

import com.taskhub.entity.Task;
import com.taskhub.entity.TaskList;

import java.util.List;


public class TaskService extends AbstractService<Task> {

    public TaskService(DAO<Task> taskDAO) {
      super(taskDAO);
    }

    @Override
    public List<Task> getByUserId(Long userId, DAO<Task> dao) {
        return List.of();
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
     public List<Task> getByTaskListId(Long taskListId, DAO<Task> taskDAO) {
         if (!(taskDAO instanceof TaskDAO)) {
             throw new IllegalArgumentException("Invalid DAO provided");
         }
         return ((TaskDAO) taskDAO).getTasksByTaskListId(taskListId);
    }


}
