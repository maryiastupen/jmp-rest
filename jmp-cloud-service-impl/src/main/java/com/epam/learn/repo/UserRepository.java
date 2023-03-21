package com.epam.learn.repo;

import com.epam.learn.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD Repository to work with {@link User} entities
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
