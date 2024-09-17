package com.fabioragno.bookstore.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
