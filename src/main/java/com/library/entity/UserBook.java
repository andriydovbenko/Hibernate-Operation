package com.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "user_books")
public class UserBook {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    public UserBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}