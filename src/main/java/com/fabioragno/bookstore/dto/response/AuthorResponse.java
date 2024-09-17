package com.fabioragno.bookstore.dto.response;

import com.fabioragno.bookstore.dao.model.BookModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
    private String biography;
    private List<String> books;
    private Long sold;
}
