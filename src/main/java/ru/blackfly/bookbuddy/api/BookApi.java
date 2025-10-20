package ru.blackfly.bookbuddy.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blackfly.bookbuddy.dto.BookDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "Книги", description = "API для управления книгами")
@RequestMapping("/api/books")
public interface BookApi {

    @Operation(summary = "Получить список всех книг")
    @GetMapping
    Page<BookDto> getBooks(
            @Parameter(description = "Номер страницы (по умолчанию 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Размер страницы (по умолчанию 10)") @RequestParam(defaultValue = "10") int size
    );

    @Operation(summary = "Получить книгу по ID")
    @GetMapping("/{id}")
    ResponseEntity<BookDto> getBookById(@Parameter(description = "UUID книги") @PathVariable UUID id);

    @Operation(summary = "Поиск книги по названию")
    @GetMapping("/search")
    ResponseEntity<List<BookDto>> getBookByTitle(@Parameter(description = "Название книги") @RequestParam String title);

    @Operation(summary = "Создать новую книгу")
    @PostMapping
    ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto book);

    @Operation(summary = "Обновить книгу по ID")
    @PutMapping("/{id}")
    ResponseEntity<BookDto> updateBook(@PathVariable UUID id, @RequestBody BookDto book);

    @Operation(summary = "Удалить книгу по ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable UUID id);
}
