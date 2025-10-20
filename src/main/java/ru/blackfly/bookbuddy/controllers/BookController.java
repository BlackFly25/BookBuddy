package ru.blackfly.bookbuddy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.blackfly.bookbuddy.api.BookApi;
import ru.blackfly.bookbuddy.dto.BookDto;
import ru.blackfly.bookbuddy.services.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class BookController implements BookApi {
    private final BookService bookService;

    @Override
    public Page<BookDto> getBooks(int page, int size) {
        return bookService.getBooks(page, size);
    }

    @Override
    public ResponseEntity<BookDto> getBookById(UUID id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    public ResponseEntity<List<BookDto>> getBookByTitle(String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @Override
    public ResponseEntity<BookDto> createBook(BookDto book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @Override
    public ResponseEntity<BookDto> updateBook(UUID id, BookDto book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @Override
    public ResponseEntity<Void> deleteBook(UUID id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
