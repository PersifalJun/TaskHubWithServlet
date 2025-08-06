package com.taskhub.service;


import com.taskhub.dao.DAO;

import com.taskhub.entity.TaskList;



public class TaskListService extends AbstractService<TaskList> {

    public TaskListService(DAO<TaskList> taskListDAO) {
        super(taskListDAO);
    }


}
