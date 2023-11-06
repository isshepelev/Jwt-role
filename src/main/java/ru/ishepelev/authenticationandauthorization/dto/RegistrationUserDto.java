package ru.ishepelev.authenticationandauthorization.dto;

import lombok.Data;


@Data
public class RegistrationUserDto {

    private String name;
    private String lastname;
    private String login;
    private String password;
    private String confirmPassword;
}
