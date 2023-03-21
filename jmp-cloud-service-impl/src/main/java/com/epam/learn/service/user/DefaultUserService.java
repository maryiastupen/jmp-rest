package com.epam.learn.service.user;

import java.util.Optional;

import com.epam.learn.entity.User;
import com.epam.learn.repo.UserRepository;
import com.epam.learn.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for working with {@link User} entities
 */
@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public DefaultUserService(UserRepository userRepository) {this.userRepository = userRepository;}

    /**
     * {@inheritDoc}
     */
    @Override
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

}
