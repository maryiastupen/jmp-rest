package com.epam.learn.dto;

import java.time.LocalDate;

import com.epam.learn.entity.User;

/**
 * Represents Subscription Request DTO
 */
public class SubscriptionRequestDto {

    private Long id;
    private User user;
    private LocalDate startDate;

    public SubscriptionRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
