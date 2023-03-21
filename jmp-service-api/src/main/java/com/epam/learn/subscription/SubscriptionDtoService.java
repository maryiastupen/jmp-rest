package com.epam.learn.subscription;

import java.util.List;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;

/**
 * Service responsible for working with {@link SubscriptionResponseDto} and {@link SubscriptionRequestDto} entities
 */
public interface SubscriptionDtoService {

    /**
     * Saves subscription
     *
     * @param subscription {@link SubscriptionRequestDto}
     * @return converted {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto saveSubscription(SubscriptionRequestDto subscription);

    /**
     * Updates subscription
     *
     * @param subscription {@link SubscriptionRequestDto}
     * @return converted {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscription);

    /**
     * Fetches existing subscription
     *
     * @param id id of subscription
     * @return converted {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto getSubscription(Long id);

    /**
     * Fetches lis of existing subscriptions
     *
     * @return converted list of {@link SubscriptionResponseDto}
     */
    List<SubscriptionResponseDto> getAllSubscription();

    /**
     * Deletes subscription by id
     *
     * @param id id of subscription
     */
    void deleteSubscription(Long id);

    /**
     * Checks whether subscription with passed id exists
     *
     * @param id id of subscription
     * @return true in case subscription exists, false - otherwise
     */
    boolean subscriptionExists(Long id);

}
