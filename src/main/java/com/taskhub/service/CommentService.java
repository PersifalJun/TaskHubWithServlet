package com.taskhub.service;


import com.taskhub.dao.CommentDAO;
import com.taskhub.dao.DAO;

import com.taskhub.dto.CommentInfo;

import com.taskhub.entity.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class CommentService extends AbstractService<Comment> {


    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public CommentService(DAO<Comment> commentDAO) {
        super(commentDAO);
    }

    @Override
    public List<Comment> getByUserId(Long userId, DAO<Comment> dao) {
        return List.of();
    }


    public List<Comment> getByTaskId(Long taskId, DAO<Comment> commentDAO) {
        if (!(commentDAO instanceof CommentDAO)) {
            throw new IllegalArgumentException("Invalid DAO provided");
        }
        return ((CommentDAO) commentDAO).getCommentsByTaskId(taskId);
    }

    public Comment create(CommentInfo commentInfo, Task task, User author) {
        Comment comment = Comment.builder()
                .content(commentInfo.getContent())
                .creationDate(LocalDateTime.parse(commentInfo.getCreationDate(),formatter))
                .author(author)
                .task(task)
                .build();
        return dao.save(comment);
    }

    public Comment edit(Long commentId, CommentInfo commentInfo) {
        Comment comment = dao.getById(commentId).orElseThrow(()->new RuntimeException("Not found"));
        comment.setContent(commentInfo.getContent());
        comment.setCreationDate(LocalDateTime.parse(commentInfo.getCreationDate(), formatter));
        return dao.update(comment);


    }


}
