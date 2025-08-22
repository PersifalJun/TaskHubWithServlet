package com.taskhub.service;

import com.taskhub.dao.DAO;

import com.taskhub.dao.TaskDAO;


import com.taskhub.dto.TaskInfo;


import com.taskhub.entity.Task;
import com.taskhub.entity.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TaskService extends AbstractService<Task> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public TaskService(DAO<Task> taskDAO) {
      super(taskDAO);
    }


    @Override
    public List<Task> getByUserId(Long userId, DAO<Task> dao) {
        return List.of();
    }



    public Task edit(Long taskId, TaskInfo taskInfo) {
        Task task = dao.getById(taskId).orElseThrow(()->new RuntimeException("Not found"));
        task.setTitle(taskInfo.getTitle());
        task.setDescription(taskInfo.getDescription());
        task.setCompleted(Boolean.parseBoolean(taskInfo.getCompleted()));
        task.setCreationDate(LocalDateTime.parse(taskInfo.getCreationDate(), formatter));
        task.setDeadline(LocalDateTime.parse(taskInfo.getDeadline(), formatter));
        return dao.update(task);


    }

    public Task create(TaskInfo taskInfo, TaskList taskList) {

        Task task = Task.builder()
                .title(taskInfo.getTitle())
                .description(taskInfo.getDescription())
                .completed(Boolean.parseBoolean(taskInfo.getCompleted()))
                .creationDate(LocalDateTime.parse(taskInfo.getCreationDate(), formatter))
                .deadline(LocalDateTime.parse(taskInfo.getDeadline(), formatter))
                .taskList(taskList)
                .build();
        return dao.save(task);
    }

     public List<Task> getByTaskListId(Long taskListId, DAO<Task> taskDAO) {
         if (!(taskDAO instanceof TaskDAO)) {
             throw new IllegalArgumentException("Invalid DAO provided");
         }
         return ((TaskDAO) taskDAO).getTasksByTaskListId(taskListId);
    }






}
