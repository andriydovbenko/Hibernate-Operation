package com.library.service;

import com.library.entity.User;

public interface UserService {

    User findUser(int id);

    void showAllUser();

    void printUserInfoByUserId(int userId);
}