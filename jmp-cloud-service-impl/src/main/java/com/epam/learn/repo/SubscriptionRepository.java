package com.epam.learn.repo;

import com.epam.learn.entity.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD Repository to work with {@link Subscription} entities
 */
@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
