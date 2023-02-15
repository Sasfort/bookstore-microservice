package com.sasfort.bookservice.services;

import com.sasfort.bookservice.dto.BookRequest;
import com.sasfort.bookservice.dto.BookResponse;
import com.sasfort.bookservice.models.Book;
import com.sasfort.bookservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .author(bookRequest.getAuthor())
                .build();

        bookRepository.save(book);
        log.info("Book {} is saved.", book.getId());

        return mapToBookResponse(book);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(this::mapToBookResponse).toList();
    }

    private BookResponse mapToBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .author(book.getAuthor())
                .build();
    }

    @Transactional(readOnly = true)
    public boolean checkBookExist(String bookId) {
        return bookRepository.findById(bookId).isPresent();
    }
}
