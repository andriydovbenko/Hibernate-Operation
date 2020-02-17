package com.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity(name = "Book")
@Table(name = "book", schema = "public")
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String title;

    private String genre;

    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author"))
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<UserBook> userBooks = new ArrayList<>();

    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                title.equals(book.title) &&
                genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre);
    }
}