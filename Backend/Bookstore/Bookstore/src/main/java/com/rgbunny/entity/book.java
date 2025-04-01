package com.rgbunny.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private Float price;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "publicationDate")
    private LocalDate publicationDate;
    @Column(name = "language")
    private String language;
    @Column(name = "readingAge")
    private int readingAge;
    @Column(name = "pages")
    private int pages;
    @Column(name = "dimension")
    private String dimension;
    @Column(name = "image")
    private String image;
}
