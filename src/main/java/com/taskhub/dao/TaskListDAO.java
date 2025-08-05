package com.taskhub.dao;

import com.taskhub.entity.TaskList;
import org.hibernate.SessionFactory;

public class TaskListDAO extends AbstractDAO<TaskList> {

    public TaskListDAO(SessionFactory sessionFactory) {
        super(TaskList.class, sessionFactory);
    }
}
