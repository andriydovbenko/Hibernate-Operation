package com.library.dao;

import com.library.entity.Author;

import java.util.List;

public interface AuthorDao  {

    Author findById(int id);

    List<Author> findAll();
}