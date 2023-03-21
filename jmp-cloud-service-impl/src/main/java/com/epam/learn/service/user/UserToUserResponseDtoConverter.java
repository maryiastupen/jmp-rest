package com.epam.learn.service.user;

import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Service, responsible for conversion of {@link User} to {@link UserResponseDto}
 */
@Service
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    /**
     * Creates {@link UserResponseDto} entity based on the {@link User}
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return converted {@link UserResponseDto}
     */
    @Override
    public UserResponseDto convert(User source) {
        return new UserResponseDto().newBuilder()
                .setId(source.getId())
                .setName(source.getName())
                .setSurname(source.getSurname())
                .setBirthday(source.getBirthday())
                .buildCreateRequestDto();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <U> Converter<User, U> andThen(Converter<? super UserResponseDto, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
