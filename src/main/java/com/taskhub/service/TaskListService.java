package com.taskhub.service;


import com.taskhub.dao.TaskListDAO;
import com.taskhub.entity.TaskList;

import java.util.List;

public class TaskListService extends AbstractService<TaskList> {

    public TaskListService(TaskListDAO taskListDAO) {
        super(taskListDAO);
    }


}
