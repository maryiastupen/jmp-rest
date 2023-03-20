package com.epam.learn.api;

import java.util.List;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.subscription.SubscriptionDtoConverterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller to manage Subscriptions
 */
@Api(tags = "subscriptions", value = "subscriptions")
@RestController("subscriptionsController")
@RequestMapping(path = ServiceController.SUBSCRIPTIONS_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {

    public static final String SUBSCRIPTIONS_BASE_URL = "/v1/subscriptions";

    private final SubscriptionDtoConverterService subscriptionService;

    @Autowired
    public ServiceController(SubscriptionDtoConverterService subscriptionService) {this.subscriptionService = subscriptionService;}

    @ApiOperation(value = "Create subscription")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Subscription has been successfully saved", response = String.class),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto createdSubscription = subscriptionService.saveOrUpdateSubscription(subscriptionRequestDto);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Update subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscription has been successfully updated", response = String.class),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@ApiParam(value = "Subscription") @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto updatedSubscription = subscriptionService.saveOrUpdateSubscription(subscriptionRequestDto);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete subscription by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Subscription has been successfully deleted"),
            @ApiResponse(code = 404, message = "Subscription is not found")
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSubscription(@PathVariable("id") Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @ApiOperation(value = "Get subscription by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Subscription details", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Digital worker is not found")
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("id") Long id) {
        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.getSubscription(id);
        return new ResponseEntity<>(subscriptionResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all subscriptions")
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        List<SubscriptionResponseDto> subscriptions = subscriptionService.getAllSubscription();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

}
