package com.rgbunny.service;

import com.rgbunny.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Page<Book> searchBooks(String searchTerm, Map<String, String> filters, String sort, int page, int size);
}
