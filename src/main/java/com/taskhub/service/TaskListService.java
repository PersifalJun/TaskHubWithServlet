package com.taskhub.service;


import com.taskhub.dao.DAO;

import com.taskhub.entity.TaskList;

import java.util.List;


public class TaskListService extends AbstractService<TaskList> {

    public TaskListService(DAO<TaskList> taskListDAO) {
        super(taskListDAO);
    }

    @Override
    public List<TaskList> getByUserId(Long userId, DAO<TaskList> projectDao) {
        return List.of();
    }


}
