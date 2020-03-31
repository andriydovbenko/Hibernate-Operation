package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.impl.BookDaoImpl;
import com.library.dao.impl.UserBookDaoImpl;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.BookUserService;
import com.library.dao.UserBookDao;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookUserServiceImp implements BookUserService {

    private final BookDao bookDao;
    private final UserBookDao userBookDao;

    public BookUserServiceImp(EntityManagerFactory factory) {
        this.bookDao = new BookDaoImpl(factory);
        this.userBookDao = new UserBookDaoImpl(factory);
    }

    @Override
    public void printAllLibraryBook() {
        bookDao.showAllBook();
    }

    @Override
    public Book getBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public void showBookByGenre(String genre) {
        bookDao.findBookByGenre(genre).forEach(System.out::println);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) {
        return bookDao.findBooksIdByAuthor(author)
                .stream().map(this::getBookById)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllUserBooks(User user) {
        List<Book> books = new ArrayList<>();
        userBookDao.getAllUserBookIds(user.getId()).forEach(integer -> books.add(getBookById(integer)));
        return books;
    }

    @Override
    public void showUserBooksByUser(User user) {
        userBookDao.getAllUserBookIds(user.getId()).forEach(integer -> System.out.println(getBookById(integer)));
    }

    @Override
    public void removeBookFromUser(User user, Book book) {
        userBookDao.removeBook(user.getId(), book.getId());
    }

    @Override
    public void addBookToUser(User user, Book book) {
        userBookDao.addBook(user, book);
    }
}