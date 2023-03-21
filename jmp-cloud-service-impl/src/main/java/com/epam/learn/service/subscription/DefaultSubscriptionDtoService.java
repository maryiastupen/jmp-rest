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
import com.epam.learn.subscription.SubscriptionDtoService;
import com.epam.learn.subscription.SubscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

/**
 * Service that works with {@link SubscriptionRequestDto} and {@link SubscriptionResponseDto}
 */
@Service
public class DefaultSubscriptionDtoService implements SubscriptionDtoService {

    private final SubscriptionService subscriptionService;
    private final SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter;
    private final SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter;

    public DefaultSubscriptionDtoService(SubscriptionService subscriptionService, SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter, SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter) {
        this.subscriptionService = subscriptionService;
        this.subscriptionToSubscriptionResponseDtoConverter = subscriptionToSubscriptionResponseDtoConverter;
        this.subscriptionRequestDtoToSubscriptionConverter = subscriptionRequestDtoToSubscriptionConverter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SubscriptionResponseDto saveSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto);
        subscription = subscriptionService.saveOrUpdateSubscription(subscription);
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Long subscriptionRequestDtoId = subscriptionRequestDto.getId();
        Subscription subscription = Optional.ofNullable(subscriptionRequestDtoId).map(id -> updateExistingSubscription(subscriptionRequestDto, id))
                .orElse(subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto));
        subscription = subscriptionService.saveOrUpdateSubscription(subscription);
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponseDto getSubscription(Long id) {
        Subscription subscription = fetchSubscription(id)
                .orElseThrow(() -> new NotFoundException(format("Subscription with provided id: %s cannot be found", id)));
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponseDto> getAllSubscription() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(subscriptionService.getAllSubscription().iterator(), Spliterator.ORDERED), false)
                .map(subscriptionToSubscriptionResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteSubscription(Long id) {
        if (subscriptionExists(id)) {
            subscriptionService.deleteSubscription(id);
        } else {
            throw new NotFoundException(format("Subscription with provided id: %s cannot be found", id));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean subscriptionExists(Long id) {
        return subscriptionService.subscriptionExists(id);
    }

    private Subscription updateExistingSubscription(SubscriptionRequestDto subscriptionRequestDto, Long id) {
        return fetchSubscription(id)
                .map((subscriptionEntity) -> {
                    subscriptionRequestDtoToSubscriptionConverter.updateExistingSubscription(subscriptionRequestDto, subscriptionEntity);
                    return subscriptionEntity;
                })
                .orElseThrow(() -> new NotFoundException(format("Subscription with provided id: %s cannot be found", id)));
    }

    private Optional<Subscription> fetchSubscription(Long id) {
        return subscriptionService.getSubscription(id);

    }

}
