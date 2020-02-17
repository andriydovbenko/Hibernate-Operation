package com.library.dao.impl;

import com.library.dao.BookDao;
import com.library.entity.Author;
import com.library.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory factory;

    public BookDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Book findBookById(int id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Book book = manager.find(Book.class, id);
        manager.getTransaction().commit();
        manager.close();
        return book;
    }

    @Override
    public void showAllBook() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        List<Book> books = manager.createNativeQuery("SELECT * FROM book", Book.class).getResultList();
        manager.getTransaction().commit();
        manager.close();
        books.forEach(book -> System.out.println(book));
    }

    @Override
    public List<Book> findBookByGenre(String genre) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT * FROM book WHERE genre =:genre", Book.class)
                .setParameter("genre", genre);
        List<Book> books = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return books;
    }

    @Override
    public List<Integer> findBooksIdByAuthor(Author author) {
        int authorId = author.getId();
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("SELECT id_book FROM library.public.author_book where id_author = "
                + authorId + "");
        List list = query.getResultList();
        List<Integer> booksId = new ArrayList<>();
        for (Object o : list) {
            booksId.add((Integer) o);
        }
        manager.getTransaction().commit();
        manager.close();
        return booksId;
    }
}