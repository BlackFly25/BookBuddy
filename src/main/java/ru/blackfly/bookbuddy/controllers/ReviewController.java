package ru.blackfly.bookbuddy.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.blackfly.bookbuddy.api.ReviewApi;
import ru.blackfly.bookbuddy.dto.ReviewDto;
import ru.blackfly.bookbuddy.services.ReviewService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Review management APIs")
public class ReviewController implements ReviewApi {
    private final ReviewService reviewService;


    @Override
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @Override
    public ResponseEntity<List<ReviewDto>> getReviewByBookId(UUID bookId) {
        return ResponseEntity.ok(reviewService.findByBookId(bookId));
    }

    @Override
    public ResponseEntity<ReviewDto> createReview(ReviewDto review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.create(review));
    }
}
