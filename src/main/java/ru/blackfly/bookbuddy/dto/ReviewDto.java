package ru.blackfly.bookbuddy.dto;

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
public class ReviewDto {
    private UUID id;
    private UUID userId;
    private UUID bookId;

    @Min(1)
    @Max(5)
    private int rating;
    private String comment;


}
