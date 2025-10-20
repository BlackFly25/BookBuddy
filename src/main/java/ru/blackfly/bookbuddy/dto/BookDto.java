package ru.blackfly.bookbuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Модель книги, используемая для передачи данных между клиентом и сервером")
public class BookDto {
    @Schema(description = "Уникальный идентификатор книги")
    private UUID id;

    @NotBlank
    @Size(min=1, max=100)
    @Schema(description = "Название книги")
    private String title;

    @NotBlank
    @Size(min=1, max=50)
    @Schema(description = "Автор книги")
    private String author;

    @NotNull
    @Min(1)
    @Schema(description = "Год публикации книги")
    private int publishedYear;
    private String genre;

}
