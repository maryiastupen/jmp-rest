package com.epam.learn.service.converter;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    @Override
    public Subscription convert(SubscriptionRequestDto source) {
        return new Subscription(source.getUser(), source.getStartDate());
    }

    @Override
    public <U> Converter<SubscriptionRequestDto, U> andThen(Converter<? super Subscription, ? extends U> after) {
        return Converter.super.andThen(after);
    }

    public void updateExistingSubscription(SubscriptionRequestDto source, Subscription existingSubscription) {
        existingSubscription.setUser(source.getUser());
        existingSubscription.setStartDate(source.getStartDate());
    }

}
