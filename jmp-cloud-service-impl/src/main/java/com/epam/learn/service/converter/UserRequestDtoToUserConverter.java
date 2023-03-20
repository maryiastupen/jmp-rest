package com.epam.learn.service.converter;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto source) {
        return new User(source.getName(), source.getSurname(), source.getBirthday());
    }

    @Override
    public <U> Converter<UserRequestDto, U> andThen(Converter<? super User, ? extends U> after) {
        return Converter.super.andThen(after);
    }

    public void updateExistingUser(UserRequestDto source, User existingUser) {
        existingUser.setName(source.getName());
        existingUser.setSurname(source.getSurname());
        existingUser.setBirthday(source.getBirthday());
    }

}
