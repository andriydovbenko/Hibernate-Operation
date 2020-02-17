package com.library.controller;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.entity.UserBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DbCreator {
    private final EntityManagerFactory factory;

    public DbCreator(EntityManagerFactory factory) {
        this.factory = factory;
        createDB();
    }

    private void createDB() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        Author author1 = new Author("William", "Shakespeare");
        Author author2 = new Author("Emily", "Dickinson");
        Author author3 = new Author("John", "Donne");
        Author author4 = new Author("Oscar", "Wilde");

        Book book1 = new Book("Ingratitude", "Classic");
        Book book2 = new Book("The Single Hound ", "Classic");
        Book book3 = new Book("The Will", "Drama");
        Book book4 = new Book("La Sainte Courtisane", "Drama");

        User user1 = new User("Adam", "Lee", 20);
        User user2 = new User("Petro", "Kim", 25);
        User user3 = new User("Vasyl", "Eag", 30);
        User user4 = new User("Tom", "Kit", 55);

        UserBook userBook1 = new UserBook(user1, book2);
        UserBook userBook2 = new UserBook(user3, book4);
        UserBook userBook3 = new UserBook(user2, book3);
        UserBook userBook4 = new UserBook(user4, book1);
        UserBook userBook5 = new UserBook(user1, book1);
        UserBook userBook6 = new UserBook(user2, book2);

        manager.persist(author1);
        manager.persist(author2);
        manager.persist(author3);
        manager.persist(author4);

        manager.persist(book1);
        manager.persist(book2);
        manager.persist(book3);
        manager.persist(book4);

        manager.persist(user1);
        manager.persist(user2);
        manager.persist(user3);
        manager.persist(user4);

        book1.getAuthors().add(author1);
        book2.getAuthors().add(author2);
        book3.getAuthors().add(author3);
        book4.getAuthors().add(author4);
        book1.getAuthors().add(author2);
        book1.getAuthors().add(author3);
        book4.getAuthors().add(author2);

        manager.persist(userBook1);
        manager.persist(userBook2);
        manager.persist(userBook3);
        manager.persist(userBook4);
        manager.persist(userBook5);
        manager.persist(userBook6);

        manager.getTransaction().commit();
        manager.close();
    }
}