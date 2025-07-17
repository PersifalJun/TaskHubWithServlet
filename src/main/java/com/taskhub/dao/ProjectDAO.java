package com.taskhub.dao;

import com.taskhub.entity.Project;
import org.hibernate.SessionFactory;

public class ProjectDAO extends AbstractHibernateDAO<Project>{

    public ProjectDAO(SessionFactory sessionFactory) {
        super(Project.class, sessionFactory);
    }
}
