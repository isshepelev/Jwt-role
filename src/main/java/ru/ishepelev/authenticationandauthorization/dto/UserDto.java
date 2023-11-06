package ru.ishepelev.authenticationandauthorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String login;
}