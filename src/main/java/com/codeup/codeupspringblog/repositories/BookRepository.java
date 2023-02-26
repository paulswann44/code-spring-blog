package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("from Book b where b.title like %:title%")
    List<Book> findByTitle(@Param("title") String title);
}