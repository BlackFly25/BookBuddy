package ru.blackfly.bookbuddy.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.blackfly.bookbuddy.dto.ReviewDto;
import ru.blackfly.bookbuddy.models.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    ReviewDto toDto(Review reviewEntity);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Review toEntity(ReviewDto reviewDto);
}
