package ru.ishepelev.authenticationandauthorization.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String name;
    private String lastname;
    private String login;
    private String password;

    @ManyToMany
    @JoinColumn(name = "users_role")
    private Collection<Role> roles;
}
