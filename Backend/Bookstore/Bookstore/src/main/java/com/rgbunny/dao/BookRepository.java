package com.rgbunny.dao;


import com.rgbunny.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Boolean existsByTitle(String title);
}
