package com.taskhub.service;

import com.taskhub.dao.UserDAO;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.User;


import java.util.List;


public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getFixedCountUsers(int offset, int limit) {
        return userDAO.getItems(offset,limit);
    }

    public User getUserById(Long id) {

        return userDAO.getById(id).orElseThrow();

    }
    public List<User>  getAllUsers() {
        return userDAO.findAll();
    }

    public void deleteUserById(Long id) {
       userDAO.deleteById(id);
    }


    public User edit(Long userId, UserInfo info) {
        User user = userDAO.getById(userId).orElseThrow(()->new RuntimeException("Not found"));
        user.setUserName(info.getUserName());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());
        return userDAO.update(user);
    }
    public User create(UserInfo info) {
        User user = new User();
        user.setUserName(info.getUserName());
        user.setEmail(info.getEmail());
        user.setPassword(info.getPassword());
        return userDAO.save(user);
    }

    public int getAllUsersCount() {
        return userDAO.getAllCount();
    }

}
