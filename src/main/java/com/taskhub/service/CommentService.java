package com.taskhub.service;

import com.taskhub.dao.AbstractDAO;
import com.taskhub.dao.CommentDAO;
import com.taskhub.dao.DAO;
import com.taskhub.entity.Comment;

import java.util.List;


public class CommentService extends AbstractService<Comment> {
    public CommentService(DAO<Comment> commentDAO) {
        super(commentDAO);
    }

    @Override
    public List<Comment> getByUserId(Long userId, DAO<Comment> projectDao) {
        return List.of();
    }


}
