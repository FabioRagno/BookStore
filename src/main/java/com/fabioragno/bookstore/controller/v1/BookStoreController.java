package com.fabioragno.bookstore.controller.v1;

import com.fabioragno.bookstore.dto.request.BookRequest;
import com.fabioragno.bookstore.dto.response.AuthorResponse;
import com.fabioragno.bookstore.dto.response.BookResponse;
import com.fabioragno.bookstore.exception.ExistingEntityException;
import com.fabioragno.bookstore.exception.MissingEntityException;
import com.fabioragno.bookstore.service.BookStoreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    /**
     * Test controller
     * @return pong if service in running fine
     */
    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    /**
     * Method to get book by id
     * @param id
     * @return book
     * @throws MissingEntityException
     */
    @GetMapping("books/{id}")
    public BookResponse getBookById(@PathVariable Long id) throws MissingEntityException {
        return bookStoreService.getBookById(id);
    }

    /**
     * Method to create new books
     * @param bookRequest
     * @return the book just created
     * @throws ExistingEntityException
     * @throws MissingEntityException
     */
    @PutMapping("books")
    public BookResponse createBook(@Valid @RequestBody BookRequest bookRequest) throws ExistingEntityException, MissingEntityException {
        return bookStoreService.createBook(bookRequest);
    }

    /**
     * Method to retrieve a list of books with quantity in stock below a specified threshold
     * @param stock
     * @return books under the treshold
     */
    @GetMapping("books")
    public List<BookResponse> getBookByStockTreshold(@RequestParam Long stock) {
        return bookStoreService.getBookByStockTreshold(stock);
    }

    /**
     * Method to get a list of the top 'N' selling authors ranked by the total number of books sold.
     * @param ranking
     * @return top 'N' selling authors
     */
    @GetMapping("authors")
    public List<AuthorResponse> getTopSeller(@RequestParam Long ranking) {
        return bookStoreService.getTopSeller(ranking);
    }

}
