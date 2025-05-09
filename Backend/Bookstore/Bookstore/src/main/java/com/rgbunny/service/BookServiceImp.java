package com.rgbunny.service;

import com.rgbunny.dao.BookRepository;
import com.rgbunny.entity.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        if(bookRepository.existsByTitle(book.getTitle()))
            throw new IllegalArgumentException("Title đã tồn tại");
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(Long id, Book updated) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Book với id = " + id));

        if(updated.getTitle() == null || updated.getTitle().equals(existing.getTitle()))
        {
            if (updated.getAuthor() != null) {
                existing.setAuthor(updated.getAuthor());
            }
            if (updated.getDescription() != null) existing.setDescription(updated.getDescription());

            if (updated.getCategory() != null) existing.setCategory(updated.getCategory());
            if (updated.getPrice() != null) existing.setPrice(updated.getPrice());
            if (updated.getPublisher() != null) existing.setPublisher(updated.getPublisher());
            if (updated.getPublicationDate() != null) existing.setPublicationDate(updated.getPublicationDate());
            if (updated.getLanguage() != null) existing.setLanguage(updated.getLanguage());
            if (updated.getReadingAge() != null) existing.setReadingAge(updated.getReadingAge());
            if (updated.getPages() != null) existing.setPages(updated.getPages());
            if (updated.getDimension() != null) existing.setDimension(updated.getDimension());

            return bookRepository.save(existing);
        }
        else if (bookRepository.existsByTitle(updated.getTitle())) {
            throw new IllegalArgumentException("Title đã tồn tại");
        }

        existing.setTitle(updated.getTitle());

        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy Book với id = " + id);
        }
        bookRepository.deleteById(id);
    }
}
