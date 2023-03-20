package com.epam.learn.user;

import java.util.Optional;

import com.epam.learn.entity.User;

public interface UserService {

    User saveOrUpdateUser(User user);

    Optional<User> getUser(Long id);

    Iterable<User> getAllUser();

    void deleteUser(Long id);

}
