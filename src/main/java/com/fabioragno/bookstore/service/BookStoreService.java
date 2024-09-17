package com.fabioragno.bookstore.service;

import com.fabioragno.bookstore.dao.model.AuthorModel;
import com.fabioragno.bookstore.dao.model.BookModel;
import com.fabioragno.bookstore.dao.repository.AuthorRepository;
import com.fabioragno.bookstore.dao.repository.BookRepository;
import com.fabioragno.bookstore.dto.request.BookRequest;
import com.fabioragno.bookstore.dto.response.AuthorResponse;
import com.fabioragno.bookstore.dto.response.BookResponse;
import com.fabioragno.bookstore.exception.ExistingEntityException;
import com.fabioragno.bookstore.exception.MissingEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreService {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreService.class);
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookStoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public BookResponse getBookById(Long id) throws MissingEntityException {

        logger.info("Incoming request for get book by id " + id);
        Optional<BookModel> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            logger.error("Can't find book with id " + id);
            throw new MissingEntityException();
        }
        return bookOpt.get().toBookResponse();
    }

    public BookResponse createBook(BookRequest bookRequest) throws ExistingEntityException, MissingEntityException {

        String isbn = bookRequest.getIsbn();
        logger.info("Trying to create new book with ISBN " + isbn);
        if (bookRepository.findByIsbn(isbn).isPresent()) {
            logger.error("Book already existing with ISBN " + isbn);
            throw new ExistingEntityException();
        }

        Long authorId = bookRequest.getAuthorId();
        logger.info("Looking for author with id " + authorId);
        Optional<AuthorModel> authorOpt = authorRepository.findById(authorId);
        if (authorOpt.isEmpty()) {
            logger.error("Can't find author with id " + authorId);
            throw new MissingEntityException();
        }

        logger.info("Let's go to insert new book with title " + bookRequest.getTitle());
        BookModel newBook = new BookModel();
        newBook.setTitle(bookRequest.getTitle());
        newBook.setAuthorModel(authorOpt.get());
        newBook.setIsbn(isbn);
        newBook.setPrice(bookRequest.getPrice());
        newBook.setStock(bookRequest.getStock());
        newBook.setSold(bookRequest.getSold());

        bookRepository.saveAndFlush(newBook);

        return newBook.toBookResponse();
    }

    public List<BookResponse> getBookByStockTreshold(Long stock) {

        logger.info("Incoming request for books with quantity in stock lower than " + stock);
        return bookRepository.findByStockLessThan(stock).stream().map(BookModel::toBookResponse).toList();
    }

    public List<AuthorResponse> getTopSeller(Long ranking) {

        logger.info("Incoming request for top " + ranking + " authors");
        return authorRepository.findAll().stream()
                .map(AuthorModel::toAuthorResponse)
                .sorted(Comparator.comparing(AuthorResponse::getSold).reversed())
                .limit(ranking).toList();
    }

}
