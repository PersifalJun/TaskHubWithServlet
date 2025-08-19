package com.taskhub.dao;

import com.taskhub.entity.Task;
import com.taskhub.entity.TaskList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TaskListDAO extends AbstractDAO<TaskList> {

    public TaskListDAO(SessionFactory sessionFactory) {
        super(TaskList.class, sessionFactory);
    }

    public TaskList getTaskListByProjectId(Long projectId) {

        TaskList result = null;
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            Query<TaskList> query = session
                    .createQuery("FROM TaskList t WHERE t.project.id = :projectId ", TaskList.class);
            query.setParameter("projectId", projectId);
            result = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new HibernateException("Getting taskList failed", e);
        }
        return result;


    }
}
