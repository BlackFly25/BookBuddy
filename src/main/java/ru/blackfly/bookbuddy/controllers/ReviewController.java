package ru.blackfly.bookbuddy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blackfly.bookbuddy.dto.ReviewDto;
import ru.blackfly.bookbuddy.models.Review;
import ru.blackfly.bookbuddy.services.ReviewService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Review management APIs")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/book/{bookId}")
    @Operation(summary = "Get review by book ID")
    public ResponseEntity<List<ReviewDto>> getReviewByBookId(@PathVariable(("bookId")) UUID id) {
        List<ReviewDto> reviews = reviewService.findByBookId(id);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    @Operation(summary = "Create a new review")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto review) {
        ReviewDto createdReview = reviewService.create(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }







}
