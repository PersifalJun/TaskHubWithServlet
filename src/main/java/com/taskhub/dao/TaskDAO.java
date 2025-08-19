package com.taskhub.dao;

import com.taskhub.entity.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(Task.class, sessionFactory);
    }

    @Override
    public List<Task> findAll() {
        List<Task> result;
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            result = session
                    .createQuery("SELECT t FROM Task t JOIN FETCH t.taskList tl LEFT JOIN FETCH tl.tasks ORDER BY t.id ASC", Task.class)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new HibernateException("findAll failed", e);
        }
        return result;
    }

    public List<Task> getTasksByTaskListId(Long taskListId) {
        List<Task> result;
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            result = session
                    .createQuery("FROM Task t WHERE t.taskList.id = :taskListId", Task.class)
                    .setParameter("taskListId", taskListId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new HibernateException("Getting tasks failed", e);
        }
        return result;
    }


}
