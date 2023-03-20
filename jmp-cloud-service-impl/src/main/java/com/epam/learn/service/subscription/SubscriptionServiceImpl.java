package com.epam.learn.service.subscription;

import java.util.Optional;

import com.epam.learn.entity.Subscription;
import com.epam.learn.repo.SubscriptionRepository;
import com.epam.learn.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for working with {@link Subscription} entities
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {this.subscriptionRepository = subscriptionRepository;}

    @Override
    public Subscription saveOrUpdateSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Optional<Subscription> getSubscription(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public Iterable<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

}