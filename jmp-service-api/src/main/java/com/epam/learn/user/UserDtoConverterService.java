package com.epam.learn.user;

import java.util.List;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;

public interface UserDtoConverterService {

    UserResponseDto saveOrUpdateUser(UserRequestDto user);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUser();

    void deleteUser(Long id);

}
