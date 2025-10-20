package ru.blackfly.bookbuddy.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blackfly.bookbuddy.dto.UserDto;

import java.util.List;
import java.util.UUID;

@Tag(name = "Пользователи", description = "API для управления пользователями")
@RequestMapping("/api/users")
public interface UserApi {

    @Operation(summary = "Получить всех пользователей")
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers();

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@Parameter(description = "UUID пользователя") @PathVariable UUID id);

    @Operation(summary = "Создать нового пользователя")
    @PostMapping
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user);

    @Operation(summary = "Обновить пользователя по ID")
    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto user);

    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id);
}
