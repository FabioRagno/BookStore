package com.fabioragno.bookstore.dao.repository;

import com.fabioragno.bookstore.dao.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
}
