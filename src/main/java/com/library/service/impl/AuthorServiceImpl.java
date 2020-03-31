package com.library.service.impl;

import com.library.dao.impl.AuthorDaoImpl;
import com.library.entity.Author;
import com.library.service.AuthorService;

import javax.persistence.EntityManagerFactory;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDaoImpl authorDao;

    public AuthorServiceImpl(EntityManagerFactory factory) {
        this.authorDao = new AuthorDaoImpl(factory);
    }

    @Override
    public Author getAuthorById(int authorId) {
        return authorDao.findById(authorId);
    }

    @Override
    public void showAllAuthor() {
        authorDao.findAll().forEach(System.out::println);
    }
}