package com.taskhub.config;

import com.taskhub.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PropertiesSessionFactoryProvider implements SessionFactoryProvider {

    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(TaskList.class)
                .buildSessionFactory();

    }
}
