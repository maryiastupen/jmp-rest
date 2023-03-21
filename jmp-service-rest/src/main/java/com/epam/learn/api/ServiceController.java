package com.epam.learn.api;

import java.util.List;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.exception.NotFoundException;
import com.epam.learn.subscription.SubscriptionDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "subscriptions", description = "Subscriptions management APIs")
@RestController("subscriptionsRestController")
@RequestMapping(path = ServiceController.SUBSCRIPTIONS_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceController {

    public static final String SUBSCRIPTIONS_BASE_URL = "/v1/subscriptions";

    private final SubscriptionDtoService subscriptionService;

    @Autowired
    public ServiceController(SubscriptionDtoService subscriptionService) {this.subscriptionService = subscriptionService;}

    @Operation(summary = "Create subscription", description = "Returns created subscription's details", tags = {"subscriptions", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = SubscriptionResponseDto.class), mediaType = "application/json")}, description = "Subscription has been successfully saved")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto createdSubscription = subscriptionService.saveSubscription(subscriptionRequestDto);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);

    }

    @Operation(summary = "Update subscription", description = "Returns updated subscription's details", tags = {"subscriptions", "update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = SubscriptionResponseDto.class), mediaType = "application/json")}, description = "Subscription has been successfully updated"),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}, description = "Subscription is not found")
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        try {
            SubscriptionResponseDto updatedSubscription = subscriptionService.updateSubscription(subscriptionRequestDto);
            return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete subscription by id", description = "Deletes subscription by passed id", tags = {"subscriptions", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}, description = "Subscription has been successfully deleted"),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}, description = "Subscription is not found")
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSubscription(@PathVariable("id") Long id) {
        try {
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>("Subscription has been successfully deleted", HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get subscription by id", description = "Returns subscription fetched by id", tags = {"subscriptions", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = SubscriptionResponseDto.class), mediaType = "application/json")}, description = "Subscription has been successfully fetched"),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}, description = "Subscription is not found")
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("id") Long id) {
        try {
            SubscriptionResponseDto subscriptionResponseDto = subscriptionService.getSubscription(id);
            return new ResponseEntity<>(subscriptionResponseDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all subscriptions", description = "Returns list of all subscriptions", tags = {"subscriptions", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SubscriptionResponseDto.class)))}, description = "Subscriptions have been successfully fetched"),
    })
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        List<SubscriptionResponseDto> subscriptions = subscriptionService.getAllSubscription();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

}
