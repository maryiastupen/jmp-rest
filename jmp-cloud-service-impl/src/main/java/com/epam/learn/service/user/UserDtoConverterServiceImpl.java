package com.epam.learn.service.user;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.entity.User;
import com.epam.learn.exception.NotFoundException;
import com.epam.learn.service.converter.UserRequestDtoToUserConverter;
import com.epam.learn.service.converter.UserToUserResponseDtoConverter;
import com.epam.learn.user.UserDtoConverterService;
import com.epam.learn.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
public class UserDtoConverterServiceImpl implements UserDtoConverterService {

    private final UserService userService;
    private final UserToUserResponseDtoConverter userToUserResponseDtoConverter;
    private final UserRequestDtoToUserConverter userRequestDtoToUserConverter;

    public UserDtoConverterServiceImpl(UserService userService, UserToUserResponseDtoConverter userToUserResponseDtoConverter, UserRequestDtoToUserConverter userRequestDtoToUserConverter) {
        this.userService = userService;
        this.userToUserResponseDtoConverter = userToUserResponseDtoConverter;
        this.userRequestDtoToUserConverter = userRequestDtoToUserConverter;
    }

    @Override
    @Transactional
    public UserResponseDto saveOrUpdateUser(UserRequestDto userRequestDto) {
        Long userRequestDtoId = userRequestDto.getId();
        User user = Optional.ofNullable(userRequestDtoId).map(id -> updateExistingUser(userRequestDto, id))
                .orElse(userRequestDtoToUserConverter.convert(userRequestDto));
        user = userService.saveOrUpdateUser(user);
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = getUserFromRepo(id)
                .orElseThrow(() -> new NotFoundException(format("User with provided id: %s cannot be found", id)));
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUser() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(userService.getAllUser().iterator(), Spliterator.ORDERED), false)
                .map(userToUserResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    private User updateExistingUser(UserRequestDto userRequestDto, Long id) {
        return getUserFromRepo(id)
                .map((userEntity) -> {
                    userRequestDtoToUserConverter.updateExistingUser(userRequestDto, userEntity);
                    return userEntity;
                })
                .orElseThrow(() -> new NotFoundException(format("User with provided id: %s cannot be found", id)));
    }

    private Optional<User> getUserFromRepo(Long id) {
        return userService.getUser(id);
    }

}
