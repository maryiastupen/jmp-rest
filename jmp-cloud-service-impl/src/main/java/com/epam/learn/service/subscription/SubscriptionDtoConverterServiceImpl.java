package com.epam.learn.service.subscription;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.entity.Subscription;
import com.epam.learn.exception.NotFoundException;
import com.epam.learn.service.converter.SubscriptionRequestDtoToSubscriptionConverter;
import com.epam.learn.service.converter.SubscriptionToSubscriptionResponseDtoConverter;
import com.epam.learn.subscription.SubscriptionDtoConverterService;
import com.epam.learn.subscription.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
public class SubscriptionDtoConverterServiceImpl implements SubscriptionDtoConverterService {

    private final SubscriptionService subscriptionService;
    private final SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter;
    private final SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter;

    public SubscriptionDtoConverterServiceImpl(SubscriptionService subscriptionService, SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter, SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter) {
        this.subscriptionService = subscriptionService;
        this.subscriptionToSubscriptionResponseDtoConverter = subscriptionToSubscriptionResponseDtoConverter;
        this.subscriptionRequestDtoToSubscriptionConverter = subscriptionRequestDtoToSubscriptionConverter;
    }

    @Override
    @Transactional
    public SubscriptionResponseDto saveOrUpdateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Long subscriptionRequestDtoId = subscriptionRequestDto.getId();
        Subscription subscription = Optional.ofNullable(subscriptionRequestDtoId).map(id -> updateExistingSubscription(subscriptionRequestDto, id))
                .orElse(subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto));
        subscription = subscriptionService.saveOrUpdateSubscription(subscription);
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponseDto getSubscription(Long id) {
        Subscription subscription = getSubscriptionFromRepo(id)
                .orElseThrow(() -> new NotFoundException(format("Subscription with provided id: %s cannot be found", id)));
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponseDto> getAllSubscription() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(subscriptionService.getAllSubscription().iterator(), Spliterator.ORDERED), false)
                .map(subscriptionToSubscriptionResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSubscription(Long id) {
        subscriptionService.deleteSubscription(id);
    }

    private Subscription updateExistingSubscription(SubscriptionRequestDto subscriptionRequestDto, Long id) {
        return getSubscriptionFromRepo(id)
                .map((subscriptionEntity) -> {
                    subscriptionRequestDtoToSubscriptionConverter.updateExistingSubscription(subscriptionRequestDto, subscriptionEntity);
                    return subscriptionEntity;
                })
                .orElseThrow(() -> new NotFoundException(format("Subscription with provided id: %s cannot be found", id)));
    }

    private Optional<Subscription> getSubscriptionFromRepo(Long id) {
        return subscriptionService.getSubscription(id);

    }

}
