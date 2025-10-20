package ru.blackfly.bookbuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Отзыв пользователя о книге")
public class ReviewDto {
    @Schema(description = "Уникальный идентификатор отзыва")
    private UUID id;
    @Schema(description = "UUID пользователя, оставившего отзыв")
    private UUID userId;
    @Schema(description = "UUID книги, к которой относится отзыв")
    private UUID bookId;

    @Min(1)
    @Max(5)
    @Schema(description = "Оценка книги (от 1 до 5)")
    private int rating;
    @Schema(description = "Текст отзыва")
    private String comment;


}
