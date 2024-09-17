package com.fabioragno.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRequest {

    @NotBlank
    private String title;
    @NotNull
    private Long authorId;
    @NotBlank
    private String isbn;
    @NotNull
    private Double price;
    @NotNull
    private Long stock;
    @NotNull
    private Long sold;
}
