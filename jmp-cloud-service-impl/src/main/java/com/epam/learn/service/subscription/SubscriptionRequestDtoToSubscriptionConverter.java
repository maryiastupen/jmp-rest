package com.epam.learn.service.subscription;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Service, responsible for conversion of {@link SubscriptionRequestDto} to {@link Subscription}
 */
@Service
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    /**
     * Creates {@link Subscription} entity based on the {@link SubscriptionRequestDto}
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return converted {@link Subscription} entity
     */
    @Override
    public Subscription convert(SubscriptionRequestDto source) {
        return new Subscription(source.getUser(), source.getStartDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <U> Converter<SubscriptionRequestDto, U> andThen(Converter<? super Subscription, ? extends U> after) {
        return Converter.super.andThen(after);
    }

    /**
     * Updates fields of existing user based on the fields of {@link SubscriptionRequestDto}
     *
     * @param subscriptionRequestDto {@link SubscriptionRequestDto}
     * @param existingSubscription   {@link Subscription} with updated fields
     */
    public void updateExistingSubscription(SubscriptionRequestDto subscriptionRequestDto, Subscription existingSubscription) {
        existingSubscription.setUser(subscriptionRequestDto.getUser());
        existingSubscription.setStartDate(subscriptionRequestDto.getStartDate());
    }

}
