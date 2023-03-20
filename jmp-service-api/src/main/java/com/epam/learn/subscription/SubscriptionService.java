package com.epam.learn.subscription;

import java.util.Optional;

import com.epam.learn.entity.Subscription;

public interface SubscriptionService {

    Subscription saveOrUpdateSubscription(Subscription user);

    Optional<Subscription> getSubscription(Long id);

    Iterable<Subscription> getAllSubscription();

    void deleteSubscription(Long id);

}
