package ru.blackfly.bookbuddy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пользователь системы BookBuddy")
public class UserDto {
    @Schema(description = "Уникальный идентификатор пользователя")
    private UUID id;

    @Schema(description = "Имя пользователя")
    private String name;

    @Email
    @NotNull
    @Schema(description = "Электронная почта пользователя")
    private String email;
}
