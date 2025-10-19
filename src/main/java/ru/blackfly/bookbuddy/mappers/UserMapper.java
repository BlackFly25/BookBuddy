package ru.blackfly.bookbuddy.mappers;

import org.mapstruct.Mapper;
import ru.blackfly.bookbuddy.dto.UserDto;
import ru.blackfly.bookbuddy.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User userEntity);
    User toEntity(UserDto userDto);
}
