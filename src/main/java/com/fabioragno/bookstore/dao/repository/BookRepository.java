package com.fabioragno.bookstore.dao.repository;

import com.fabioragno.bookstore.dao.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookModel, Long> {

    Optional<BookModel> findByIsbn(String isbn);
    List<BookModel> findByStockLessThan(Long stock);
}
