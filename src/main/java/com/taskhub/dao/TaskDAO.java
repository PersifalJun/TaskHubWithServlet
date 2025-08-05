package com.taskhub.dao;

import com.taskhub.entity.Task;
import org.hibernate.SessionFactory;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(Task.class, sessionFactory);
    }
}
