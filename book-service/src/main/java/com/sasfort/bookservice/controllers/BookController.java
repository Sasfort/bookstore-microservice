package com.sasfort.bookservice.controllers;

import com.sasfort.bookservice.dto.BookRequest;
import com.sasfort.bookservice.dto.BookResponse;
import com.sasfort.bookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkBookExist(@PathVariable("book-id") String bookId) {
        return bookService.checkBookExist(bookId);
    }
}
