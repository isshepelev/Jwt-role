package ru.ishepelev.authenticationandauthorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ishepelev.authenticationandauthorization.domain.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
public class RegistrationUserDto {

    private String name;
    private String lastname;
    private String login;
    private String password;
    private String confirmPassword;
}
