package com.taskhub.dao;

import com.taskhub.entity.Comment;
import org.hibernate.SessionFactory;

public class CommentDAO extends AbstractDAO<Comment> {

    public CommentDAO(SessionFactory sessionFactory) {
        super(Comment.class,sessionFactory);
    }



}
