package com.rgbunny.controller;

import com.rgbunny.entity.Book;
import com.rgbunny.service.BookServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    public BookController(BookServiceImp bookServiceImp) {
        this.bookServiceImp = bookServiceImp;
    }

    private final BookServiceImp bookServiceImp;

    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookServiceImp.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/api/books")
    public Book createBook(@RequestBody @Valid Book book){
        return bookServiceImp.createBook(book);
    }

    @PatchMapping("/api/books")
    public Book updateBook(@RequestParam Long id, @RequestBody Book book){
        return bookServiceImp.updateBook(id, book);
    }

    @DeleteMapping("/api/books")
    public void deleteBook(@RequestParam Long id){
        bookServiceImp.deleteBook(id);
    }
}
