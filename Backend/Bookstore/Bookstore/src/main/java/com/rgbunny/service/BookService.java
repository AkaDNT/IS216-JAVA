package com.rgbunny.service;

import com.rgbunny.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
