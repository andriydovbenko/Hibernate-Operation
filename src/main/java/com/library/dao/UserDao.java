package com.library.dao;

import com.library.entity.User;

import java.util.List;

public interface UserDao {

    User findUser(int id);

    List<User> getAllUsers();
}