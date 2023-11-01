package ru.ishepelev.authenticationandauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class AuthenticationAndAuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationAndAuthorizationApplication.class, args);
    }

    @PostConstruct
    public addUser()
}
