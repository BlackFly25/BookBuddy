package ru.blackfly.bookbuddy.mappers;

import org.mapstruct.Mapper;
import ru.blackfly.bookbuddy.dto.BookDto;
import ru.blackfly.bookbuddy.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book bookEntity);
    Book toEntity(BookDto bookDto);
}
