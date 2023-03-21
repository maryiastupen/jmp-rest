package com.epam.learn.service.user;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Service, responsible for conversion of {@link UserRequestDto} to {@link User}
 */
@Service
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {

    /**
     * Creates {@link User} entity based on the {@link UserRequestDto}
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return converted {@link User} entity
     */
    @Override
    public User convert(UserRequestDto source) {
        return new User(source.getName(), source.getSurname(), source.getBirthday());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <U> Converter<UserRequestDto, U> andThen(Converter<? super User, ? extends U> after) {
        return Converter.super.andThen(after);
    }

    /**
     * Updates fields of existing user based on the fields of {@link UserRequestDto}
     *
     * @param userRequestDto {@link UserRequestDto}
     * @param existingUser   {@link User} with updated fields
     */
    public void updateExistingUser(UserRequestDto userRequestDto, User existingUser) {
        existingUser.setName(userRequestDto.getName());
        existingUser.setSurname(userRequestDto.getSurname());
        existingUser.setBirthday(userRequestDto.getBirthday());
    }

}
