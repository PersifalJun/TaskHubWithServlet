package com.taskhub.service;

import com.taskhub.dao.DAO;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.User;



public class UserService extends AbstractService<User> {
    public UserService(DAO<User> userDAO) {
        super(userDAO);
    }


    public User edit(Long userId, UserInfo info) {
        User user = dao.getById(userId).orElseThrow(()->new RuntimeException("Not found"));
        user.setUserName(info.getUserName());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());
        return dao.update(user);
    }

    public User create(UserInfo info) {
        User user = new User();
        user.setUserName(info.getUserName());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());
        return dao.save(user);
    }



}
