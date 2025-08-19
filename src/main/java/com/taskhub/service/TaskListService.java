package com.taskhub.service;


import com.taskhub.dao.DAO;


import com.taskhub.dao.TaskDAO;
import com.taskhub.dao.TaskListDAO;
import com.taskhub.entity.Task;
import com.taskhub.entity.TaskList;

import java.util.List;


public class TaskListService extends AbstractService<TaskList> {

    public TaskListService(DAO<TaskList> taskListDAO) {
        super(taskListDAO);
    }

    @Override
    public List<TaskList> getByUserId(Long userId, DAO<TaskList> dao) {
        return List.of();
    }

    public TaskList getByProjectId(Long projectId, DAO<TaskList> taskListDAO) {
        if (!(taskListDAO instanceof TaskListDAO)) {
            throw new IllegalArgumentException("Invalid DAO provided");
        }
        return ((TaskListDAO) taskListDAO).getTaskListByProjectId(projectId);
    }




}
