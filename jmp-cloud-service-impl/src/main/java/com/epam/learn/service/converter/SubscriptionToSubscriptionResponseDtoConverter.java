package com.epam.learn.service.converter;

import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription source) {
        return new SubscriptionResponseDto().newBuilder()
                .setId(source.getId())
                .setUser(source.getUser())
                .setStartDate(source.getStartDate())
                .buildSubscriptionResponseDto();
    }

    @Override
    public <U> Converter<Subscription, U> andThen(Converter<? super SubscriptionResponseDto, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
