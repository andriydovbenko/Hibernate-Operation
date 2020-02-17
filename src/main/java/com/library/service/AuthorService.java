package com.library.service;

import com.library.entity.Author;

public interface AuthorService {

    Author getAuthorById(int authorId);

    void showAllAuthor();
}