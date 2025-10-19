package ru.blackfly.bookbuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private int publishedYear;
    private String genre;

}
