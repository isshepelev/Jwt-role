package ru.ishepelev.authenticationandauthorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ishepelev.authenticationandauthorization.domain.Role;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String login;
}