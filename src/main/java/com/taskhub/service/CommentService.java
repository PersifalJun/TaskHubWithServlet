package com.taskhub.service;

import com.taskhub.dao.AbstractDAO;
import com.taskhub.dao.CommentDAO;
import com.taskhub.dao.DAO;
import com.taskhub.entity.Comment;


public class CommentService extends AbstractService<Comment> {
    public CommentService(DAO<Comment> commentDAO) {
        super(commentDAO);
    }


}
