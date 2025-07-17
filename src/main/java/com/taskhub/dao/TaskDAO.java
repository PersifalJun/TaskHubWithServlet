package com.taskhub.dao;

import com.taskhub.entity.Task;
import org.hibernate.SessionFactory;

public class TaskDAO extends AbstractHibernateDAO<Task>{

    public TaskDAO(SessionFactory sessionFactory) {
        super(Task.class, sessionFactory);
    }
}
