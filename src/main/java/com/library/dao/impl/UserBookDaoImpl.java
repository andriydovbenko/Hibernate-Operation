package com.library.dao.impl;

import com.library.dao.UserBookDao;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.entity.UserBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class UserBookDaoImpl implements UserBookDao {
    private final String BOOK_TABLE = "library.public.user_books";
    private final EntityManagerFactory factory;

    public UserBookDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Integer> getAllUserBookIds(int userId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT b.book_id FROM " + BOOK_TABLE + " b " +
                "WHERE user_id_user = :id");
        query.setParameter("id", userId);
        List<Integer> listBookIds = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return listBookIds;
    }

    @Override
    public void removeBook(int userId, int bookId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("delete FROM UserBook WHERE user.id = " + userId + " and book.id = "
                + bookId + "");
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public void addBook(User user, Book book) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        UserBook userBook = new UserBook(user, book);
        manager.persist(userBook);
        manager.getTransaction().commit();
    }
}