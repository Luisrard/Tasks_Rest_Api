package com.nextline.mappers;

import com.nextline.entities.User;
import com.nextline.models.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
