package com.epam.learn.user;

import java.util.Optional;

import com.epam.learn.entity.User;

/**
 * Service for working with {@link User} entities
 */
public interface UserService {

    /**
     * Saves or updates {@link User}
     *
     * @param user {@link User} entity
     * @return saved or updated {@link User}
     */
    User saveOrUpdateUser(User user);

    /**
     * Fetches existing user by id
     *
     * @param id id of {@link User} entity
     * @return optional {@link User} entity
     */
    Optional<User> getUser(Long id);

    /**
     * Fetches all users
     *
     * @return all users
     */
    Iterable<User> getAllUser();

    /**
     * Deletes {@link User} by id
     *
     * @param id id of {@link User} entity
     */
    void deleteUser(Long id);

    /**
     * Checks if user with passed id exists
     *
     * @param id id of {@link User} entity
     * @return true in case user exists, false - otherwise
     */
    boolean userExists(Long id);

}
