package com.library.service;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.User;

import java.util.List;

public interface BookUserService {

    void printAllLibraryBook();

    Book getBookById(int id);

    void showBookByGenre(String genre);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getAllUserBooks(User user);

    void showUserBooksByUser(User user);

    void removeBookFromUser(User user, Book book);

    void addBookToUser(User user, Book book);
}