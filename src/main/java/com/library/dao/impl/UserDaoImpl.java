package com.library.dao.impl;

import com.library.dao.UserDao;
import com.library.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory factory;

    public UserDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public User findUser(int id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class, id);
        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT * FROM library.public.user", User.class);
        List<User> userList = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return userList;
    }
}