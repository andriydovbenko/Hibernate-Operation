package com.library.dao;

import com.library.entity.Book;
import com.library.entity.User;

import java.util.List;

public interface UserBookDao {

    List<Integer> getAllUserBookIds(int userId);

    void removeBook(int userId, int bookId);

    void addBook(User user, Book book);
}