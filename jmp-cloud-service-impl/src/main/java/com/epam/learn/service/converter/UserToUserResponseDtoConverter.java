package com.epam.learn.service.converter;

import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User source) {
        return new UserResponseDto().newBuilder()
                .setId(source.getId())
                .setName(source.getName())
                .setSurname(source.getSurname())
                .setBirthday(source.getBirthday())
                .buildCreateRequestDto();
    }

    @Override
    public <U> Converter<User, U> andThen(Converter<? super UserResponseDto, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
