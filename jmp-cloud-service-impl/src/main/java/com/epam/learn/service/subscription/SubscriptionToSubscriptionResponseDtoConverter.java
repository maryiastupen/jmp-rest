package com.epam.learn.service.subscription;

import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Service, responsible for conversion of {@link Subscription} to {@link SubscriptionResponseDto}
 */
@Service
public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    /**
     * Creates {@link SubscriptionResponseDto} entity based on the {@link Subscription}
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return converted {@link SubscriptionResponseDto}
     */
    @Override
    public SubscriptionResponseDto convert(Subscription source) {
        return new SubscriptionResponseDto().newBuilder()
                .setId(source.getId())
                .setUser(source.getUser())
                .setStartDate(source.getStartDate())
                .buildSubscriptionResponseDto();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <U> Converter<Subscription, U> andThen(Converter<? super SubscriptionResponseDto, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
