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
public class DefaultSubscriptionService implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * {@inheritDoc}
     */
    @Autowired
    public DefaultSubscriptionService(SubscriptionRepository subscriptionRepository) {this.subscriptionRepository = subscriptionRepository;}

    /**
     * {@inheritDoc}
     */
    @Override
    public Subscription saveOrUpdateSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Subscription> getSubscription(Long id) {
        return subscriptionRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean subscriptionExists(Long id) {
        return subscriptionRepository.existsById(id);
    }

}