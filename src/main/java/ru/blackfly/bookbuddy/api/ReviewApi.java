package ru.blackfly.bookbuddy.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blackfly.bookbuddy.dto.ReviewDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "Отзывы", description = "API для управления отзывами")
@RequestMapping("/api/reviews")
public interface ReviewApi {

    @Operation(summary = "Получить все отзывы")
    @GetMapping
    ResponseEntity<List<ReviewDto>> getAllReviews();

    @Operation(summary = "Получить отзывы по книге")
    @GetMapping("/book/{bookId}")
    ResponseEntity<List<ReviewDto>> getReviewByBookId(@Parameter(description = "UUID книги") @PathVariable UUID bookId);

    @Operation(summary = "Создать новый отзыв")

    @PostMapping
    ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto review);
}
