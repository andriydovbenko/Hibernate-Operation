package com.library.dao.impl;

import com.library.entity.Author;
import com.library.dao.AuthorDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private final EntityManagerFactory factory;

    public AuthorDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Author findById(int id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Author author = (Author) manager.createNativeQuery("SELECT * FROM author where id = :id", Author.class)
                .setParameter("id", id).getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return author;
    }

    @Override
    public List<Author> findAll() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT * FROM author", Author.class);
        @SuppressWarnings("unchecked") List<Author> authorList = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return authorList;
    }
}