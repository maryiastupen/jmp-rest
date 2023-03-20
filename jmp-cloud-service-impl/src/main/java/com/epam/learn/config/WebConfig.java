package com.epam.learn.config;

import com.epam.learn.service.converter.SubscriptionRequestDtoToSubscriptionConverter;
import com.epam.learn.service.converter.SubscriptionToSubscriptionResponseDtoConverter;
import com.epam.learn.service.converter.UserRequestDtoToUserConverter;
import com.epam.learn.service.converter.UserToUserResponseDtoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserRequestDtoToUserConverter());
        registry.addConverter(new UserToUserResponseDtoConverter());
        registry.addConverter(new SubscriptionRequestDtoToSubscriptionConverter());
        registry.addConverter(new SubscriptionToSubscriptionResponseDtoConverter());
    }

}