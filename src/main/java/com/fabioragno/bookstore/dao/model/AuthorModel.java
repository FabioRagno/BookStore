package com.fabioragno.bookstore.dao.model;

import com.fabioragno.bookstore.dto.response.AuthorResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class AuthorModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String biography;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authorModel")
    private List<BookModel> bookModels;

    public AuthorResponse toAuthorResponse() {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(id);
        authorResponse.setName(name);
        authorResponse.setBiography(biography);
        authorResponse.setBooks(bookModels.stream().map(BookModel::getIsbn).toList());
        authorResponse.setSold(getTotalSoldBooks());
        return authorResponse;
    }

    public Long getTotalSoldBooks() {
        return bookModels.stream().mapToLong(BookModel::getSold).sum();
    }
}
