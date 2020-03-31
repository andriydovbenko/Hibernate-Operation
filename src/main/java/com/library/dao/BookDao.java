package com.library.dao;

import com.library.entity.Author;
import com.library.entity.Book;

import java.util.List;

public interface BookDao {

    Book findBookById(int id);

    void showAllBook();

    List<Book> findBookByGenre(String genre);

    List<Integer> findBooksIdByAuthor(Author author);
}