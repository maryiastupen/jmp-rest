package com.epam.learn.dto;

import java.time.LocalDate;

import com.epam.learn.entity.User;

/**
 * Represents Subscription Response DTO
 */
public class SubscriptionResponseDto {

    private Long id;
    private User user;
    private LocalDate startDate;

    public SubscriptionResponseDto() {
    }

    public SubscriptionResponseDto(Long id, User user, LocalDate startDate) {
        this.id = id;
        this.user = user;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public SubscriptionResponseDto.SubscriptionResponseDtoBuilder newBuilder() {
        return new SubscriptionResponseDto.SubscriptionResponseDtoBuilder();
    }

    public static class SubscriptionResponseDtoBuilder {

        private Long id;
        private User user;
        private LocalDate startDate;

        public SubscriptionResponseDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public SubscriptionResponseDtoBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public SubscriptionResponseDtoBuilder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public SubscriptionResponseDto buildSubscriptionResponseDto() {
            return new SubscriptionResponseDto(id, user, startDate);
        }

    }

}
