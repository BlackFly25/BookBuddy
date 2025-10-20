package ru.blackfly.bookbuddy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.blackfly.bookbuddy.api.UserApi;
import ru.blackfly.bookbuddy.dto.UserDto;
import ru.blackfly.bookbuddy.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<UserDto> getUserById(UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UUID id, UserDto user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
