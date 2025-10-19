package ru.blackfly.bookbuddy.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Size(min=1, max=100)
    private String title;

    @NotBlank
    @Size(min=1, max=50)
    private String author;

    @NotNull
    @Min(1)
    private int publishedYear;
    private String genre;

}
