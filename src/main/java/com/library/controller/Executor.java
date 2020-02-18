package com.library.controller;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.AuthorService;
import com.library.service.BookUserService;
import com.library.service.UserService;
import com.library.service.impl.AuthorServiceImpl;
import com.library.service.impl.BookUserServiceImp;
import com.library.service.impl.UserServiceImpl;
import com.library.view.Menu;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class Executor {
    private static final String GENRE_CLASSIC = "Classic";
    private static final String GENRE_DRAMA = "Drama";
    private final AuthorService authorService;
    private final BookUserService bookUserService;
    private final UserService userService;
    private final Menu menu = new Menu();
    private final Scanner scanner;
    private User user;
    private Author temporaryAuthor;
    private Book temporaryBook;
    private boolean isUserChosen;
    private boolean isAppRunning = true;
    private boolean isAuthorChosen = false;
    private boolean isBookChosen = false;

    public Executor(EntityManagerFactory factory) {
        new TableInitializer(factory);
        this.authorService = new AuthorServiceImpl(factory);
        this.bookUserService = new BookUserServiceImp(factory);
        this.userService = new UserServiceImpl(factory);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        menu.showUserChoice();
        userService.showAllUser();
        chooseUser();
        runMainMenuLoop();
    }

    private void runMainMenuLoop() {
        while (isAppRunning) {
            menu.printMenu();
            String menuChoice = scanner.next();
            switch (menuChoice) {
                case "1":
                    userService.printUserInfoByUserId(user.getId());
                    break;
                case "2":
                    showUserBasket();
                    break;
                case "3":
                    getGenreChoice();
                    break;
                case "4":
                    showBookByAuthor();
                    break;
                case "5":
                    addBookToTheBasket();
                    break;
                case "6":
                    removeBookFromUserBasket();
                    break;
                case "0":
                    exitFromApp();
            }
        }
    }

    private void removeBookFromUserBasket() {
        showUserBasket();
        chooseBook();
        bookUserService.removeBookFromUser(user, temporaryBook);
        temporaryBook = null;
    }

    private void showUserBasket() {
        menu.printBasket();
        bookUserService.showUserBooksByUser(user);
    }

    private void exitFromApp() {
        isAppRunning = false;
        scanner.close();
        System.exit(0);
    }

    private void showBookByAuthor() {
        chooseAuthor();
        menu.printAuthorBooks();
        printAllAuthorBooks();
        temporaryAuthor = null;
    }

    private void addBookToTheBasket() {
        bookUserService.printAllLibraryBook();
        chooseBook();
        checkUserBasket();
        temporaryBook = null;
        showUserBasket();
    }

    private void checkUserBasket() {
        List<Book> bookList = bookUserService.getAllUserBooks(user);
        if (bookList.isEmpty()) {
            addBook();
        } else {
            long result = bookList.stream().filter(book -> book.equals(temporaryBook)).count();
            if (result == 0) {
                addBook();
            }
        }
    }

    private void addBook() {
        bookUserService.addBookToUser(user, temporaryBook);
    }

    private void chooseBook() {
        while (!isBookChosen) {
            String bookChoice = scanner.next();
            try {
                int bookIdChoice = Integer.parseInt(bookChoice);
                temporaryBook = bookUserService.getBookById(bookIdChoice);
                if (temporaryBook == null) {
                    throw new Exception();
                } else {
                    isBookChosen = true;
                }
            } catch (Exception e) {
                menu.printWrongChoice();
            }
        }
        isBookChosen = false;
    }

    private void printAllAuthorBooks() {
        bookUserService.getBooksByAuthor(temporaryAuthor)
                .forEach(book -> System.out.println(book.toString()));
    }

    private void chooseAuthor() {
        while (!isAuthorChosen) {
            menu.printDescriptionForAuthorChoosing();
            authorService.showAllAuthor();
            String authorChoice = scanner.next();
            try {
                int authorId = Integer.parseInt(authorChoice);
                temporaryAuthor = authorService.getAuthorById(authorId);
                if (temporaryAuthor == null) {
                    throw new Exception();
                } else {
                    isAuthorChosen = true;
                }
            } catch (Exception e) {
                menu.printWrongChoice();
            }
        }
        isAuthorChosen = false;
    }

    private void getGenreChoice() {
        menu.printAvailableGenre();
        String genreChoice = scanner.next();
        if (genreChoice.equals("1")) {
            bookUserService.showBookByGenre(GENRE_CLASSIC);
        } else if (genreChoice.equals("2")) {
            bookUserService.showBookByGenre(GENRE_DRAMA);
        } else {
            menu.printWrongChoice();
        }
    }

    private void chooseUser() {
        while (!isUserChosen) {
            String userChoice = scanner.next();
            try {
                int userChoiceParsedToInt = Integer.parseInt(userChoice);
                user = userService.findUser(userChoiceParsedToInt);
                if (user == null) {
                    throw new Exception();
                } else {
                    isUserChosen = true;
                    menu.printCorrectChoice();
                }
            } catch (Exception e) {
                menu.printWrongChoice();
            }
        }
    }
}