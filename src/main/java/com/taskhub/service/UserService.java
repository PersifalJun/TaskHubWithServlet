package com.taskhub.service;

import com.taskhub.dao.DAO;

import com.taskhub.dto.UserInfo;
import com.taskhub.entity.User;

import java.util.List;


public class UserService extends AbstractService<User> {
    public UserService(DAO<User> userDAO) {
        super(userDAO);
    }

    @Override
    public List<User> getByUserId(Long userId, DAO<User> projectDao) {
        return List.of();
    }




    public User edit(Long userId, UserInfo info) {
        User user = dao.getById(userId).orElseThrow(()->new RuntimeException("Not found"));
        user.setUserName(info.getUserName());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());
        return dao.update(user);
    }

    public User create(UserInfo info) {
        User user = User.builder()
                .userName(info.getUserName())
                .email(info.getEmail())
                .password(info.getPassword())
                .build();

        return dao.save(user);
    }



}
