package com.epam.learn.user;

import java.util.List;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;

/**
 * Service responsible for working with {@link UserResponseDto} and {@link UserRequestDto} entities
 */
public interface UserDtoService {

    /**
     * Saves user
     *
     * @param user {@link UserRequestDto}
     * @return converted {@link UserResponseDto}
     */
    UserResponseDto saveUser(UserRequestDto user);

    /**
     * Updates user
     *
     * @param user {@link UserRequestDto}
     * @return converted {@link UserResponseDto}
     */
    UserResponseDto updateUser(UserRequestDto user);

    /**
     * Fetches existing user
     *
     * @param id id of user
     * @return converted {@link UserResponseDto}
     */
    UserResponseDto getUser(Long id);

    /**
     * Fetches lis of existing users
     *
     * @return converted list of {@link UserResponseDto}
     */
    List<UserResponseDto> getAllUser();

    /**
     * Deletes user by id
     *
     * @param id id of user
     */
    void deleteUser(Long id);

    /**
     * Checks whether user with passed id exists
     *
     * @param id id of user
     * @return true in case user exists, false - otherwise
     */
    boolean userExists(Long id);

}
