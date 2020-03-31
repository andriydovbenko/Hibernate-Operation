package com.library.view;

import java.util.logging.Logger;

public class Menu {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public void showUserChoice() {
        log.info("\nFor choosing user select user id (only digit) :\n");
    }

    public void printMenu() {
        log.info("Select the scenario:\n\n1. Show user info\n2. Show user basket.\n3. Show book by genre" +
                "\n4. Show book by author\n5. Add book to basket\n6. Remove Book from user basket. \n0. Exit\n");
    }

    public void printWrongChoice() {
        log.info("\n...Inappropriate input!!!\nTry again:\n");
    }

    public void printCorrectChoice() {
        log.info("\n...User was chosen correctly...\n");
    }

    public void printAvailableGenre() {
        log.info("\n1. Classic.\n2. Drama.\n");
    }

    public void printDescriptionForAuthorChoosing() {
        log.info("\nFro choosing Author select author id (only digit):\n");
    }

    public void printBasket() {
        log.info("\nYour basket:\n");
    }

    public void printAuthorBooks() {
        log.info("\nBooks By Author:\n");
    }
}