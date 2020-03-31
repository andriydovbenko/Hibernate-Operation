package com.library.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("SyntaxError")
@Getter
@ToString(exclude = "books")
@NoArgsConstructor
@Entity(name = "Author")
@Table(name = "author", schema = "public")
public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int  id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;

    @Getter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "id_author"),
            inverseJoinColumns = @JoinColumn(name = "id_book"))
    private List<Book> books = new ArrayList<>();

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}