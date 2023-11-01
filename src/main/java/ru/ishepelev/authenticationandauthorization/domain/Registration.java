package ru.ishepelev.authenticationandauthorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String login;
    private String password;
    private String repeatPassword;
    private String name;
    private String lastname;
}
