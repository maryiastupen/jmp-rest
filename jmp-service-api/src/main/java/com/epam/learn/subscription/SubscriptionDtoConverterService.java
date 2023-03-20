package com.epam.learn.subscription;

import java.util.List;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;

public interface SubscriptionDtoConverterService {

    SubscriptionResponseDto saveOrUpdateSubscription(SubscriptionRequestDto user);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscription();

    void deleteSubscription(Long id);

}
