package ru.blackfly.bookbuddy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min=1, max=100)
    private String title;
    private String author;
    private int publishedYear;
    private String genre;

}
