package com.rookies4.myspringbootlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@DynamicUpdate
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(unique = true,nullable = false)
    private String isbn;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private LocalDate publishDate;

    protected Book() {
    }

    public Book(String isbn, String title, String author, Integer price, LocalDate publishDate){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishDate = publishDate;
    }
}
