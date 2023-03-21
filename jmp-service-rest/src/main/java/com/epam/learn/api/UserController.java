package com.epam.learn.api;

import java.util.List;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.exception.NotFoundException;
import com.epam.learn.user.UserDtoService;
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
 * REST API controller to manage Users
 */
@Tag(name = "users", description = "Users management APIs")
@RestController("usersRestController")
@RequestMapping(path = UserController.USERS_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String USERS_BASE_URL = "/v1/users";

    private final UserDtoService userService;

    @Autowired
    public UserController(UserDtoService userService) {this.userService = userService;}

    @Operation(summary = "Create user", description = "Returns created user's details", tags = {"users", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = UserResponseDto.class), mediaType = "application/json")}, description = "User has been successfully saved")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.saveUser(userRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @Operation(summary = "Update user", description = "Returns updated user's details", tags = {"users", "update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserResponseDto.class), mediaType = "application/json")}, description = "User has been successfully updated"),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}, description = "User is not found")
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto updatedUser = userService.updateUser(userRequestDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete user by id", description = "Deletes user by passed id", tags = {"users", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}, description = "User has been successfully deleted"),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}, description = "User is not found")
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User has been successfully deleted", HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get user by id", description = "Returns user fetched by id", tags = {"users", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserResponseDto.class), mediaType = "application/json")}, description = "User has been successfully fetched"),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}, description = "User is not found")
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        try {
            UserResponseDto userResponseDto = userService.getUser(id);
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all users", description = "Returns list of all users", tags = {"users", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))}, description = "Users have been successfully fetched"),
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<UserResponseDto> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
