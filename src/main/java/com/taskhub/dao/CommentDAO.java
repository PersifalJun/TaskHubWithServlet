package com.taskhub.dao;

import com.taskhub.entity.Comment;
import com.taskhub.entity.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CommentDAO extends AbstractDAO<Comment> {

    public CommentDAO(SessionFactory sessionFactory) {
        super(Comment.class,sessionFactory);
    }

    public List<Comment> getCommentsByTaskId(Long taskId) {
        List<Comment> comments = null;
        try{
            Session session = getCurrentSession();
            session.beginTransaction();
            Query<Comment> query = session.createNativeQuery("select * from comments where task_id = :taskId order by id", Comment.class);
            query.setParameter("taskId", taskId);
            comments = query.getResultList();
            session.getTransaction().commit();
        }
        catch (HibernateException ex){
            ex.printStackTrace();
        }
        return comments;
    }

}
