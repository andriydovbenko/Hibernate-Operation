package com.library.service.impl;

import com.library.dao.impl.UserDaoImpl;
import com.library.entity.User;
import com.library.service.UserService;

import javax.persistence.EntityManagerFactory;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(EntityManagerFactory factory) {
        this.userDao = new UserDaoImpl(factory);
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    @Override
    public void showAllUser() {
        userDao.getAllUsers().forEach(user -> System.out.println(user.toString()));
    }

    @Override
    public void printUserInfoByUserId(int userId) {
        System.out.println(findUser(userId).toString());
    }
}