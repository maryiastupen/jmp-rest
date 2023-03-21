package com.epam.learn.subscription;

import java.util.Optional;

import com.epam.learn.entity.Subscription;

/**
 * Service for working with {@link Subscription} entities
 */
public interface SubscriptionService {

    /**
     * Saves or updates {@link Subscription}
     *
     * @param subscription {@link Subscription} entity
     * @return saved or updated {@link Subscription}
     */
    Subscription saveOrUpdateSubscription(Subscription subscription);

    /**
     * Fetches existing subscription by id
     *
     * @param id id of {@link Subscription} entity
     * @return optional {@link Subscription} entity
     */
    Optional<Subscription> getSubscription(Long id);

    /**
     * Fetches all subscriptions
     *
     * @return all subscriptions
     */
    Iterable<Subscription> getAllSubscription();

    /**
     * Deletes {@link Subscription} by id
     *
     * @param id id of {@link Subscription} entity
     */
    void deleteSubscription(Long id);

    /**
     * Checks if subscription with passed id exists
     *
     * @param id id of {@link Subscription} entity
     * @return true in case subscription exists, false - otherwise
     */
    boolean subscriptionExists(Long id);

}
