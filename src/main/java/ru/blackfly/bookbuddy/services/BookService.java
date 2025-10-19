package ru.blackfly.bookbuddy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.blackfly.bookbuddy.dto.BookDto;
import ru.blackfly.bookbuddy.mappers.BookMapper;
import ru.blackfly.bookbuddy.models.Book;
import ru.blackfly.bookbuddy.repos.BookRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto findById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return bookMapper.toDto(book);
    }

    public List<BookDto> findByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return books
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDto create(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Transactional
    public BookDto update(UUID id, BookDto bookDto) {
        Book toUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        toUpdate.setTitle(bookDto.getTitle());
        toUpdate.setAuthor(bookDto.getAuthor());
        toUpdate.setPublishedYear(bookDto.getPublishedYear());
        toUpdate.setGenre(bookDto.getGenre());

        Book savedBook = bookRepository.save(toUpdate);
        return bookMapper.toDto(savedBook);
    }

    @Transactional
    public void delete(UUID id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.deleteById(id);
    }

    public Page<BookDto> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(bookMapper::toDto);

    }

}
