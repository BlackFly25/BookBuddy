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
public class ReviewDto {
    private UUID id;
    private UUID userId;
    private UUID bookId;
    private int rating;
    private String comment;


}
