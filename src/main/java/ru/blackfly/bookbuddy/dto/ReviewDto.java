package ru.blackfly.bookbuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.blackfly.bookbuddy.models.User;
import ru.blackfly.bookbuddy.models.Book;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private UUID id;
    private User user;
    private Book book;
    private int rating;
    private String comment;


}
