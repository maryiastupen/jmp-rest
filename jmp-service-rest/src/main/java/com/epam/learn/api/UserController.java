package com.epam.learn.api;

import java.util.List;

import com.epam.learn.dto.UserRequestDto;
import com.epam.learn.dto.UserResponseDto;
import com.epam.learn.user.UserDtoConverterService;
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
 * REST API controller to manage Users
 */
@Api(tags = "users", value = "users")
@RestController("usersRestController")
@RequestMapping(path = UserController.USERS_BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String USERS_BASE_URL = "/v1/users";

    private final UserDtoConverterService userService;

    @Autowired
    public UserController(UserDtoConverterService userService) {this.userService = userService;}

    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User has been successfully saved", response = String.class),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.saveOrUpdateUser(userRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User has been successfully updated", response = String.class),
            @ApiResponse(code = 400, message = "Invalid request parameters"),
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> updateUser(@ApiParam(value = "User") @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto updatedUser = userService.saveOrUpdateUser(userRequestDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User has been successfully deleted"),
            @ApiResponse(code = 404, message = "User is not found")
    })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @ApiOperation(value = "Get user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User details", response = UserResponseDto.class),
            @ApiResponse(code = 404, message = "User is not found")
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        UserResponseDto userResponseDto = userService.getUser(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<UserResponseDto> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
