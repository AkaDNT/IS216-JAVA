package com.rgbunny.controller;

import com.rgbunny.entity.Book;
import com.rgbunny.service.BookServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    public BookController(BookServiceImp bookServiceImp) {
        this.bookServiceImp = bookServiceImp;
    }

    private BookServiceImp bookServiceImp;

    @GetMapping("/api/books")
    public List<Book> getAllBooks(){
        return bookServiceImp.getAllBooks();
    }
}
