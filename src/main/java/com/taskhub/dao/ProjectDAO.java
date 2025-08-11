package com.taskhub.dao;

import com.taskhub.entity.Project;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;


public class ProjectDAO extends AbstractDAO<Project> {

    public ProjectDAO(SessionFactory sessionFactory) {
        super(Project.class, sessionFactory);
    }

    public List<Project> getProjectByUserId(Long userId) {
        List<Project> projects = null;
        try{
            Session session = getCurrentSession();
            session.beginTransaction();
            Query<Project> query = session.createNativeQuery("select * from projects where user_id = :userId order by id", Project.class);
            query.setParameter("userId", userId);
            projects = query.getResultList();
            session.getTransaction().commit();
        }
        catch (HibernateException ex){
            ex.printStackTrace();
        }
        return projects;
    }


}
