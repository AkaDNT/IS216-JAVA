package com.rgbunny.dao;


import com.rgbunny.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Boolean existsByTitle(String title);

    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = ?1")
    void deleteBookById(Long id);
}
