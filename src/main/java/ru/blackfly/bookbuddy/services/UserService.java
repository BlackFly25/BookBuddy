package ru.blackfly.bookbuddy.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.blackfly.bookbuddy.dto.UserDto;
import ru.blackfly.bookbuddy.mappers.UserMapper;
import ru.blackfly.bookbuddy.models.User;
import ru.blackfly.bookbuddy.repos.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    public final UserMapper userMapper;

    public UserDto findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id " + id + " not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
       return users
               .stream().map(userMapper::toDto)
               .collect(Collectors.toList());
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserDto update(UUID id, UserDto userDto) {
        User toUpdate = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User with id " + id + " not found"));
        toUpdate.setName(userDto.getName());
        toUpdate.setEmail(userDto.getEmail());

        User savedUser = userRepository.save(toUpdate);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public void delete(UUID id) {
        userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User with id " + id + " not found"));
        userRepository.deleteById(id);
    }

}
