package com.library.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@EqualsAndHashCode(exclude = "userBooks")
@ToString(exclude ="userBooks")
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user", updatable = false, nullable = false)
    private int  id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int age;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private List<UserBook> userBooks = new ArrayList<>();

    public User(String name, String lastName, int age) {
        this.firstName = name;
        this.lastName = lastName;
        this.age = age;
    }
}