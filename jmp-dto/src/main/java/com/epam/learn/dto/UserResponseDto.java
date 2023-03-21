package com.epam.learn.dto;

import java.time.LocalDate;

/**
 * Represents User Response DTO
 */
public class UserResponseDto {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthday;

    public UserResponseDto() {
    }

    public UserResponseDtoBuilder newBuilder() {
        return new UserResponseDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public UserResponseDto(Long id, String name, String surname, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public static class UserResponseDtoBuilder {

        private Long id;
        private String name;
        private String surname;
        private LocalDate birthday;

        public UserResponseDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserResponseDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserResponseDtoBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserResponseDtoBuilder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserResponseDto buildCreateRequestDto() {
            return new UserResponseDto(id, name, surname, birthday);
        }

    }

}
