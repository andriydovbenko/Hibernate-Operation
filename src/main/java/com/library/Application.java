package com.library;

import com.library.controller.Executor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
        new Executor(factory).start();
        factory.close();
    }
}