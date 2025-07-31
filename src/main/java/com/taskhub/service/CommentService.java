package com.taskhub.service;

import com.taskhub.dao.CommentDAO;
import com.taskhub.entity.Comment;


import java.util.List;

public class CommentService {
    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getFixedCountComments(int offset, int limit) {
        return  commentDAO.getItems(offset,limit);
    }

    public Comment getCommentById(Long id) {
        return commentDAO.getById(id).orElseThrow();

    }
    public List<Comment> getAllComments() {
        return commentDAO.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentDAO.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentDAO.deleteById(id);
    }

    public Comment updateComment(Comment comment) {
        return commentDAO.update(comment);
    }
    public int getAllCommentsCount() {
        return commentDAO.getAllCount();
    }
}
