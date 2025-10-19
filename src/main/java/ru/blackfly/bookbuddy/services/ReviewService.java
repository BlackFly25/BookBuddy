package ru.blackfly.bookbuddy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.blackfly.bookbuddy.dto.ReviewDto;
import ru.blackfly.bookbuddy.mappers.ReviewMapper;
import ru.blackfly.bookbuddy.models.Book;
import ru.blackfly.bookbuddy.models.Review;
import ru.blackfly.bookbuddy.models.User;
import ru.blackfly.bookbuddy.repos.BookRepository;
import ru.blackfly.bookbuddy.repos.ReviewRepository;
import ru.blackfly.bookbuddy.repos.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto findById(UUID id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found with id: " + id));
    return reviewMapper.toDto(review);
    }

    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews
                .stream().
                map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<ReviewDto> findByBookId(UUID bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new RuntimeException("Book not found with id: " + bookId);
        }
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviews
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public ReviewDto create(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);

        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(()->new RuntimeException("User not found with id: " + reviewDto.getUserId()));

        Book book = bookRepository.findById(reviewDto.getBookId())
                .orElseThrow(()->new RuntimeException("Book not found with id: " + reviewDto.getBookId()));
    review.setUser(user);
    review.setBook(book);
        if (reviewRepository.existsByUserIdAndBookId(reviewDto.getUserId(), reviewDto.getBookId())) {
            throw new RuntimeException("User already reviewed this book");
        }

        Review savedReview = reviewRepository.save(review);
    return reviewMapper.toDto(savedReview);
    }

    @Transactional
    public ReviewDto update(UUID id,ReviewDto reviewDto) {
        Review toUpdate = reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found with id: " + id));

        toUpdate.setComment(reviewDto.getComment());
        toUpdate.setRating(reviewDto.getRating());

        if(reviewDto.getUserId() != null) {
            User user = userRepository.findById(reviewDto.getUserId())
                    .orElseThrow(()->new RuntimeException("User not found with id: " + reviewDto.getUserId()));
            toUpdate.setUser(user);
        }
        if(reviewDto.getBookId() != null) {
            Book book = bookRepository.findById(reviewDto.getBookId())
                    .orElseThrow(()->new RuntimeException("Book not found with id: " + reviewDto.getBookId()));
            toUpdate.setBook(book);
        }
        Review savedReview = reviewRepository.save(toUpdate);
        return reviewMapper.toDto(savedReview);
    }

    @Transactional
    public void delete(UUID id) {
        reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review not found with id: " + id));
        reviewRepository.deleteById(id);
    }

}
