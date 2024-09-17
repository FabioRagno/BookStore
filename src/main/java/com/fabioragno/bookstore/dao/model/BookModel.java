package com.fabioragno.bookstore.dao.model;

import com.fabioragno.bookstore.dto.response.BookResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;
    private String isbn;
    private Double price;
    private Long stock;
    private Long sold;

    public BookResponse toBookResponse() {
        BookResponse bookDto = new BookResponse();
        bookDto.setId(id);
        bookDto.setTitle(title);
        bookDto.setAuthor(authorModel.getName());
        bookDto.setIsbn(isbn);
        bookDto.setPrice(price);
        bookDto.setStock(stock);
        bookDto.setSold(sold);
        return bookDto;
    }
}
